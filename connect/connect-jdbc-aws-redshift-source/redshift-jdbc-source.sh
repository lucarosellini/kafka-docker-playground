#!/bin/bash
set -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"
source ${DIR}/../../scripts/utils.sh

if [ ! -f ${DIR}/RedshiftJDBC4-1.2.20.1043.jar ]
then
     wget https://s3.amazonaws.com/redshift-downloads/drivers/jdbc/1.2.20.1043/RedshiftJDBC4-1.2.20.1043.jar
fi

if [ ! -f $HOME/.aws/config ]
then
     logerror "ERROR: $HOME/.aws/config is not set"
     exit 1
fi
if [ -z "$AWS_CREDENTIALS_FILE_NAME" ]
then
    export AWS_CREDENTIALS_FILE_NAME="credentials"
fi
if [ ! -f $HOME/.aws/$AWS_CREDENTIALS_FILE_NAME ]
then
     logerror "ERROR: $HOME/.aws/$AWS_CREDENTIALS_FILE_NAME is not set"
     exit 1
fi

if [[ "$TAG" == *ubi8 ]] || version_gt $TAG_BASE "5.9.0"
then
     export CONNECT_CONTAINER_HOME_DIR="/home/appuser"
else
     export CONNECT_CONTAINER_HOME_DIR="/root"
fi

${DIR}/../../environment/plaintext/start.sh "${PWD}/docker-compose.plaintext.yml"

CLUSTER_NAME=pg${USER}jdbcredshift${TAG}
CLUSTER_NAME=${CLUSTER_NAME//[-._]/}

set +e
log "Delete AWS Redshift cluster, if required"
aws redshift delete-cluster --cluster-identifier $CLUSTER_NAME --skip-final-cluster-snapshot
log "Delete security group sg$CLUSTER_NAME, if required"
aws ec2 delete-security-group --group-name sg$CLUSTER_NAME
set -e

log "Create AWS Redshift cluster"
# https://docs.aws.amazon.com/redshift/latest/mgmt/getting-started-cli.html
aws redshift create-cluster --cluster-identifier $CLUSTER_NAME --master-username masteruser --master-user-password myPassword1 --node-type dc2.large --cluster-type single-node --publicly-accessible

# Verify AWS Redshift cluster has started within MAX_WAIT seconds
MAX_WAIT=480
CUR_WAIT=0
log "⌛ Waiting up to $MAX_WAIT seconds for AWS Redshift cluster $CLUSTER_NAME to start"
aws redshift describe-clusters --cluster-identifier $CLUSTER_NAME | jq .Clusters[0].ClusterStatus > /tmp/out.txt 2>&1
while [[ ! $(cat /tmp/out.txt) =~ "available" ]]; do
     sleep 10
     aws redshift describe-clusters --cluster-identifier $CLUSTER_NAME | jq .Clusters[0].ClusterStatus > /tmp/out.txt 2>&1
     CUR_WAIT=$(( CUR_WAIT+10 ))
     if [[ "$CUR_WAIT" -gt "$MAX_WAIT" ]]; then
          echo -e "\nERROR: The logs in ${CONTROL_CENTER_CONTAINER} container do not show 'available' after $MAX_WAIT seconds. Please troubleshoot with 'docker container ps' and 'docker container logs'.\n"
          exit 1
     fi
done
log "AWS Redshift cluster $CLUSTER_NAME has started!"

log "Create a security group"
GROUP_ID=$(aws ec2 create-security-group --group-name sg$CLUSTER_NAME --description "playground aws redshift" | jq -r .GroupId)
log "Allow ingress traffic from 0.0.0.0/0 on port 5439"
aws ec2 authorize-security-group-ingress --group-id $GROUP_ID --protocol tcp --port 5439 --cidr "0.0.0.0/0"
log "Modify AWS Redshift cluster to use the security group $GROUP_ID"
aws redshift modify-cluster --cluster-identifier $CLUSTER_NAME --vpc-security-group-ids $GROUP_ID

# getting cluster URL
CLUSTER=$(aws redshift describe-clusters --cluster-identifier $CLUSTER_NAME | jq -r .Clusters[0].Endpoint.Address)

set +e
docker run -i -e CLUSTER="$CLUSTER" -v "${DIR}/customers.sql":/tmp/customers.sql debezium/postgres:10 psql -h "$CLUSTER" -U "masteruser" -d "dev" -p "5439" << EOF
myPassword1
DROP TABLE CUSTOMERS;
EOF
set -e

log "Create database in Redshift"
docker run -i -e CLUSTER="$CLUSTER" -v "${DIR}/customers.sql":/tmp/customers.sql debezium/postgres:10 psql -h "$CLUSTER" -U "masteruser" -d "dev" -p "5439" -f "/tmp/customers.sql" << EOF
myPassword1
EOF

log "Verify data is in Redshift"
docker run -i -e CLUSTER="$CLUSTER" -v "${DIR}/customers.sql":/tmp/customers.sql debezium/postgres:10 psql -h "$CLUSTER" -U "masteruser" -d "dev" -p "5439" << EOF
myPassword1
SELECT * from CUSTOMERS;
EOF

log "Creating JDBC AWS Redshift source connector"
curl -X PUT \
     -H "Content-Type: application/json" \
     --data '{
               "connector.class": "io.confluent.connect.jdbc.JdbcSourceConnector",
               "tasks.max": "1",
               "connection.url": "jdbc:postgresql://'"$CLUSTER"':'"$PORT"'/dev?user=masteruser&password=myPassword1&ssl=false",
               "table.whitelist": "customers",
               "mode": "timestamp+incrementing",
               "timestamp.column.name": "update_ts",
               "incrementing.column.name": "id",
               "topic.prefix": "redshift-",
               "validate.non.null":"false",
               "errors.log.enable": "true",
               "errors.log.include.messages": "true"
          }' \
     http://localhost:8083/connectors/redshift-jdbc-source/config | jq .


sleep 5

log "Verifying topic redshift-customers"
timeout 60 docker exec connect kafka-avro-console-consumer -bootstrap-server broker:9092 --property schema.registry.url=http://schema-registry:8081 --topic redshift-customers --from-beginning --max-messages 5
