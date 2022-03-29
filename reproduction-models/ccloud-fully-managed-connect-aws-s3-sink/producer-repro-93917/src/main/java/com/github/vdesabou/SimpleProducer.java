package com.github.vdesabou;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.errors.TopicExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.PodamFactory;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import com.github.vdesabou.server_canvas_click_bookmark;
import com.github.vdesabou.server_canvas_device_info;
import com.github.vdesabou.server_canvas_fb_auth;
import com.github.vdesabou.server_canvas_login;
import com.github.vdesabou.server_canvas_page_load;
import com.github.vdesabou.server_club_challenge_mission_complete;
import com.github.vdesabou.server_club_contribution;
import com.github.vdesabou.server_club_create;
import com.github.vdesabou.server_club_delete;
import com.github.vdesabou.server_club_donation;
import com.github.vdesabou.server_club_edit;
import com.github.vdesabou.server_club_invite;
import com.github.vdesabou.server_club_level_up;
import com.github.vdesabou.server_club_like;
import com.github.vdesabou.server_club_list;
import com.github.vdesabou.server_club_request;
import com.github.vdesabou.server_club_user_join;
import com.github.vdesabou.server_club_user_leave;
import com.github.vdesabou.server_club_user_promotion;
import com.github.vdesabou.server_club_user_remove;
import com.github.vdesabou.server_big_win;
import com.github.vdesabou.server_bonus;
import com.github.vdesabou.server_deal;
import com.github.vdesabou.server_draw;
import com.github.vdesabou.server_freespin_trigger;
import com.github.vdesabou.server_gamble;
import com.github.vdesabou.server_slot_unlock;
import com.github.vdesabou.server_spin;
import com.github.vdesabou.server_jackpot_claim;
import com.github.vdesabou.server_jackpot_compensation;
import com.github.vdesabou.server_jackpot_reel;
import com.github.vdesabou.server_collecting_game_pack_open;
import com.github.vdesabou.server_collecting_game_piece_convert;
import com.github.vdesabou.server_collecting_game_win;
import com.github.vdesabou.server_epic_album_earn_card;
import com.github.vdesabou.server_meta_game_gifts;
import com.github.vdesabou.server_meta_game_requests;
import com.github.vdesabou.server_scratch;
import com.github.vdesabou.server_buy_bonus_bbb;
import com.github.vdesabou.server_buy_bonus_compensation;
import com.github.vdesabou.server_challenge;
import com.github.vdesabou.server_challenge_issue;
import com.github.vdesabou.server_challenge_start;
import com.github.vdesabou.server_chat;
import com.github.vdesabou.server_chat_report;
import com.github.vdesabou.server_click_fb_share;
import com.github.vdesabou.server_collect_all;
import com.github.vdesabou.server_compensation;
import com.github.vdesabou.server_concurrent;
import com.github.vdesabou.server_daily_wheel;
import com.github.vdesabou.server_device_id_to_onesignal_id;
import com.github.vdesabou.server_email_connect;
import com.github.vdesabou.server_fb_connect;
import com.github.vdesabou.server_fb_friend;
import com.github.vdesabou.server_friend_delete;
import com.github.vdesabou.server_friend_request_accept;
import com.github.vdesabou.server_friend_request_reject;
import com.github.vdesabou.server_friend_request_send;
import com.github.vdesabou.server_gem_purchase;
import com.github.vdesabou.server_inbox_reward;
import com.github.vdesabou.server_kudo_reward;
import com.github.vdesabou.server_login;
import com.github.vdesabou.server_lucky_five_win;
import com.github.vdesabou.server_lucky_spin;
import com.github.vdesabou.server_mute;
import com.github.vdesabou.server_new_user_auto_passive_event;
import com.github.vdesabou.server_onesignal_create_notification;
import com.github.vdesabou.server_programmed_win;
import com.github.vdesabou.server_purchase;
import com.github.vdesabou.server_purchased_mega_wheel;
import com.github.vdesabou.server_report;
import com.github.vdesabou.server_reward;
import com.github.vdesabou.server_smart_pot_of_gold;
import com.github.vdesabou.server_survey_reward;
import com.github.vdesabou.server_tier_match_offer_claim;
import com.github.vdesabou.server_tutorial_reward;
import com.github.vdesabou.server_unexpected_error;
import com.github.vdesabou.server_uninstall;
import com.github.vdesabou.server_update_popup_force;
import com.github.vdesabou.server_user_expired;
import com.github.vdesabou.server_vip_deal;
import com.github.vdesabou.server_vip_deal_redeem;
import com.github.vdesabou.server_edit_profile;
import com.github.vdesabou.server_epic_album_reward;
import com.github.vdesabou.server_club_league_point;
import com.github.vdesabou.server_send_social_credit;
import com.github.vdesabou.server_gamble_result;
import com.github.vdesabou.server_apple_connect;
import com.github.vdesabou.server_canvas_click_app_icon;
import com.github.vdesabou.server_pre_bbb;
import com.github.vdesabou.server_free_gem_booster;
import com.github.vdesabou.server_season_pass_point_win;
import com.github.vdesabou.server_season_pass_reward;
import com.github.vdesabou.server_announcement_setting;
import com.github.vdesabou.server_season_pass_level_up;
import com.github.vdesabou.server_gem_jackpot_spin;
import com.github.vdesabou.server_season_pass_reset;
import com.github.vdesabou.server_terms_of_use_agree;
import com.github.vdesabou.server_boss_raiders_contribute;
import com.github.vdesabou.server_boss_raiders_spin;
import com.github.vdesabou.server_boss_raiders_bonus;
import com.github.vdesabou.server_club_boss_raiders_league_point;
import com.github.vdesabou.server_club_boss_raiders_result;
import com.github.vdesabou.server_inbox_reward_expired;
import com.github.vdesabou.server_lucky_spin_expired;
import com.github.vdesabou.server_bonus_spin_expired;
import com.github.vdesabou.server_play;
import com.github.vdesabou.server_club_send_leader_push;
import com.github.vdesabou.server_leader_push_open;
import com.github.vdesabou.server_get_spin_deal;
import com.github.vdesabou.server_play_spin_deal;
import com.github.vdesabou.server_club_arena_contribute;
import com.github.vdesabou.server_club_arena_match;
import com.github.vdesabou.server_club_arena_spin;
import com.github.vdesabou.server_system_chat;
import com.github.vdesabou.server_hog_game_enter;
import com.github.vdesabou.server_hog_result;
import com.github.vdesabou.server_hog_unlock;
import com.github.vdesabou.server_hog_contribute;
import com.github.vdesabou.server_hog_deal_compensation;

public class SimpleProducer {

    private static final String KAFKA_ENV_PREFIX = "KAFKA_";
    private final Logger logger = LoggerFactory.getLogger(SimpleProducer.class);
    private final Properties properties;
    private final String topicName;
    private final Long messageBackOff;
    private Long nbMessages;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        SimpleProducer simpleProducer = new SimpleProducer();
        simpleProducer.start();
    }

    public SimpleProducer() throws ExecutionException, InterruptedException {
        properties = buildProperties(defaultProps, System.getenv(), KAFKA_ENV_PREFIX);
        topicName = System.getenv().getOrDefault("TOPIC", "sample");
        messageBackOff = Long.valueOf(System.getenv().getOrDefault("MESSAGE_BACKOFF", "100"));

        final Integer numberOfPartitions = Integer.valueOf(System.getenv().getOrDefault("NUMBER_OF_PARTITIONS", "2"));
        final Short replicationFactor = Short.valueOf(System.getenv().getOrDefault("REPLICATION_FACTOR", "3"));
        nbMessages = Long.valueOf(System.getenv().getOrDefault("NB_MESSAGES", "10"));
        if (nbMessages == -1) {
            nbMessages = Long.MAX_VALUE;
        }

        AdminClient adminClient = KafkaAdminClient.create(properties);
        createTopic(adminClient, topicName, numberOfPartitions, replicationFactor);
    }

    private void start() throws InterruptedException {
        logger.info("creating producer with props: {}", properties);

        logger.info("Sending data to `{}` topic", topicName);

        // PodamFactory factory = new PodamFactoryImpl();
        EasyRandomParameters parameters = new EasyRandomParameters()
                // .seed(123L)
                .objectPoolSize(10)
                .randomizationDepth(10)
                .stringLengthRange(1, 5)
                .collectionSizeRange(1, 5)
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(false)
                .ignoreRandomizationErrors(false);
        EasyRandom generator = new EasyRandom(parameters);

        Producer<Long, server_canvas_click_bookmark> server_canvas_click_bookmark_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_canvas_device_info> server_canvas_device_info_producer = new KafkaProducer<>(properties);
        Producer<Long, server_canvas_fb_auth> server_canvas_fb_auth_producer = new KafkaProducer<>(properties);
        Producer<Long, server_canvas_login> server_canvas_login_producer = new KafkaProducer<>(properties);
        Producer<Long, server_canvas_page_load> server_canvas_page_load_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_challenge_mission_complete> server_club_challenge_mission_complete_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_club_contribution> server_club_contribution_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_create> server_club_create_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_delete> server_club_delete_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_donation> server_club_donation_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_edit> server_club_edit_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_invite> server_club_invite_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_level_up> server_club_level_up_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_like> server_club_like_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_list> server_club_list_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_request> server_club_request_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_user_join> server_club_user_join_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_user_leave> server_club_user_leave_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_user_promotion> server_club_user_promotion_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_club_user_remove> server_club_user_remove_producer = new KafkaProducer<>(properties);
        Producer<Long, server_big_win> server_big_win_producer = new KafkaProducer<>(properties);
        Producer<Long, server_bonus> server_bonus_producer = new KafkaProducer<>(properties);
        Producer<Long, server_deal> server_deal_producer = new KafkaProducer<>(properties);
        Producer<Long, server_draw> server_draw_producer = new KafkaProducer<>(properties);
        Producer<Long, server_freespin_trigger> server_freespin_trigger_producer = new KafkaProducer<>(properties);
        Producer<Long, server_gamble> server_gamble_producer = new KafkaProducer<>(properties);
        Producer<Long, server_slot_unlock> server_slot_unlock_producer = new KafkaProducer<>(properties);
        Producer<Long, server_spin> server_spin_producer = new KafkaProducer<>(properties);
        Producer<Long, server_jackpot_claim> server_jackpot_claim_producer = new KafkaProducer<>(properties);
        Producer<Long, server_jackpot_compensation> server_jackpot_compensation_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_jackpot_reel> server_jackpot_reel_producer = new KafkaProducer<>(properties);
        Producer<Long, server_collecting_game_pack_open> server_collecting_game_pack_open_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_collecting_game_piece_convert> server_collecting_game_piece_convert_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_collecting_game_win> server_collecting_game_win_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_epic_album_earn_card> server_epic_album_earn_card_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_meta_game_gifts> server_meta_game_gifts_producer = new KafkaProducer<>(properties);
        Producer<Long, server_meta_game_requests> server_meta_game_requests_producer = new KafkaProducer<>(properties);
        Producer<Long, server_scratch> server_scratch_producer = new KafkaProducer<>(properties);
        Producer<Long, server_buy_bonus_bbb> server_buy_bonus_bbb_producer = new KafkaProducer<>(properties);
        Producer<Long, server_buy_bonus_compensation> server_buy_bonus_compensation_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_challenge> server_challenge_producer = new KafkaProducer<>(properties);
        Producer<Long, server_challenge_issue> server_challenge_issue_producer = new KafkaProducer<>(properties);
        Producer<Long, server_challenge_start> server_challenge_start_producer = new KafkaProducer<>(properties);
        Producer<Long, server_chat> server_chat_producer = new KafkaProducer<>(properties);
        Producer<Long, server_chat_report> server_chat_report_producer = new KafkaProducer<>(properties);
        Producer<Long, server_click_fb_share> server_click_fb_share_producer = new KafkaProducer<>(properties);
        Producer<Long, server_collect_all> server_collect_all_producer = new KafkaProducer<>(properties);
        Producer<Long, server_compensation> server_compensation_producer = new KafkaProducer<>(properties);
        Producer<Long, server_concurrent> server_concurrent_producer = new KafkaProducer<>(properties);
        Producer<Long, server_daily_wheel> server_daily_wheel_producer = new KafkaProducer<>(properties);
        Producer<Long, server_device_id_to_onesignal_id> server_device_id_to_onesignal_id_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_email_connect> server_email_connect_producer = new KafkaProducer<>(properties);
        Producer<Long, server_fb_connect> server_fb_connect_producer = new KafkaProducer<>(properties);
        Producer<Long, server_fb_friend> server_fb_friend_producer = new KafkaProducer<>(properties);
        Producer<Long, server_friend_delete> server_friend_delete_producer = new KafkaProducer<>(properties);
        Producer<Long, server_friend_request_accept> server_friend_request_accept_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_friend_request_reject> server_friend_request_reject_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_friend_request_send> server_friend_request_send_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_gem_purchase> server_gem_purchase_producer = new KafkaProducer<>(properties);
        Producer<Long, server_inbox_reward> server_inbox_reward_producer = new KafkaProducer<>(properties);
        Producer<Long, server_kudo_reward> server_kudo_reward_producer = new KafkaProducer<>(properties);
        Producer<Long, server_login> server_login_producer = new KafkaProducer<>(properties);
        Producer<Long, server_lucky_five_win> server_lucky_five_win_producer = new KafkaProducer<>(properties);
        Producer<Long, server_lucky_spin> server_lucky_spin_producer = new KafkaProducer<>(properties);
        Producer<Long, server_mute> server_mute_producer = new KafkaProducer<>(properties);
        Producer<Long, server_new_user_auto_passive_event> server_new_user_auto_passive_event_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_onesignal_create_notification> server_onesignal_create_notification_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_programmed_win> server_programmed_win_producer = new KafkaProducer<>(properties);
        Producer<Long, server_purchase> server_purchase_producer = new KafkaProducer<>(properties);
        Producer<Long, server_purchased_mega_wheel> server_purchased_mega_wheel_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_report> server_report_producer = new KafkaProducer<>(properties);
        Producer<Long, server_reward> server_reward_producer = new KafkaProducer<>(properties);
        Producer<Long, server_smart_pot_of_gold> server_smart_pot_of_gold_producer = new KafkaProducer<>(properties);
        Producer<Long, server_survey_reward> server_survey_reward_producer = new KafkaProducer<>(properties);
        Producer<Long, server_tier_match_offer_claim> server_tier_match_offer_claim_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_tutorial_reward> server_tutorial_reward_producer = new KafkaProducer<>(properties);
        Producer<Long, server_unexpected_error> server_unexpected_error_producer = new KafkaProducer<>(properties);
        Producer<Long, server_uninstall> server_uninstall_producer = new KafkaProducer<>(properties);
        Producer<Long, server_update_popup_force> server_update_popup_force_producer = new KafkaProducer<>(properties);
        Producer<Long, server_user_expired> server_user_expired_producer = new KafkaProducer<>(properties);
        Producer<Long, server_vip_deal> server_vip_deal_producer = new KafkaProducer<>(properties);
        Producer<Long, server_vip_deal_redeem> server_vip_deal_redeem_producer = new KafkaProducer<>(properties);
        Producer<Long, server_edit_profile> server_edit_profile_producer = new KafkaProducer<>(properties);
        Producer<Long, server_epic_album_reward> server_epic_album_reward_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_league_point> server_club_league_point_producer = new KafkaProducer<>(properties);
        Producer<Long, server_send_social_credit> server_send_social_credit_producer = new KafkaProducer<>(properties);
        Producer<Long, server_gamble_result> server_gamble_result_producer = new KafkaProducer<>(properties);
        Producer<Long, server_apple_connect> server_apple_connect_producer = new KafkaProducer<>(properties);
        Producer<Long, server_canvas_click_app_icon> server_canvas_click_app_icon_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_pre_bbb> server_pre_bbb_producer = new KafkaProducer<>(properties);
        Producer<Long, server_free_gem_booster> server_free_gem_booster_producer = new KafkaProducer<>(properties);
        Producer<Long, server_season_pass_point_win> server_season_pass_point_win_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_season_pass_reward> server_season_pass_reward_producer = new KafkaProducer<>(properties);
        Producer<Long, server_announcement_setting> server_announcement_setting_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_season_pass_level_up> server_season_pass_level_up_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_gem_jackpot_spin> server_gem_jackpot_spin_producer = new KafkaProducer<>(properties);
        Producer<Long, server_season_pass_reset> server_season_pass_reset_producer = new KafkaProducer<>(properties);
        Producer<Long, server_terms_of_use_agree> server_terms_of_use_agree_producer = new KafkaProducer<>(properties);
        Producer<Long, server_boss_raiders_contribute> server_boss_raiders_contribute_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_boss_raiders_spin> server_boss_raiders_spin_producer = new KafkaProducer<>(properties);
        Producer<Long, server_boss_raiders_bonus> server_boss_raiders_bonus_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_boss_raiders_league_point> server_club_boss_raiders_league_point_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_club_boss_raiders_result> server_club_boss_raiders_result_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_inbox_reward_expired> server_inbox_reward_expired_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_lucky_spin_expired> server_lucky_spin_expired_producer = new KafkaProducer<>(properties);
        Producer<Long, server_bonus_spin_expired> server_bonus_spin_expired_producer = new KafkaProducer<>(properties);
        Producer<Long, server_play> server_play_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_send_leader_push> server_club_send_leader_push_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_leader_push_open> server_leader_push_open_producer = new KafkaProducer<>(properties);
        Producer<Long, server_get_spin_deal> server_get_spin_deal_producer = new KafkaProducer<>(properties);
        Producer<Long, server_play_spin_deal> server_play_spin_deal_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_arena_contribute> server_club_arena_contribute_producer = new KafkaProducer<>(
                properties);
        Producer<Long, server_club_arena_match> server_club_arena_match_producer = new KafkaProducer<>(properties);
        Producer<Long, server_club_arena_spin> server_club_arena_spin_producer = new KafkaProducer<>(properties);
        Producer<Long, server_system_chat> server_system_chat_producer = new KafkaProducer<>(properties);
        Producer<Long, server_hog_game_enter> server_hog_game_enter_producer = new KafkaProducer<>(properties);
        Producer<Long, server_hog_result> server_hog_result_producer = new KafkaProducer<>(properties);
        Producer<Long, server_hog_unlock> server_hog_unlock_producer = new KafkaProducer<>(properties);
        Producer<Long, server_hog_contribute> server_hog_contribute_producer = new KafkaProducer<>(properties);
        Producer<Long, server_hog_deal_compensation> server_hog_deal_compensation_producer = new KafkaProducer<>(
                properties);

        long id = 0;
        while (id < nbMessages) {

            server_canvas_click_bookmark server_canvas_click_bookmark = generator
                    .nextObject(server_canvas_click_bookmark.class);
            server_canvas_device_info server_canvas_device_info = generator.nextObject(server_canvas_device_info.class);
            server_canvas_fb_auth server_canvas_fb_auth = generator.nextObject(server_canvas_fb_auth.class);
            server_canvas_login server_canvas_login = generator.nextObject(server_canvas_login.class);
            server_canvas_page_load server_canvas_page_load = generator.nextObject(server_canvas_page_load.class);
            server_club_challenge_mission_complete server_club_challenge_mission_complete = generator
                    .nextObject(server_club_challenge_mission_complete.class);
            server_club_contribution server_club_contribution = generator.nextObject(server_club_contribution.class);
            server_club_create server_club_create = generator.nextObject(server_club_create.class);
            server_club_delete server_club_delete = generator.nextObject(server_club_delete.class);
            server_club_donation server_club_donation = generator.nextObject(server_club_donation.class);
            server_club_edit server_club_edit = generator.nextObject(server_club_edit.class);
            server_club_invite server_club_invite = generator.nextObject(server_club_invite.class);
            server_club_level_up server_club_level_up = generator.nextObject(server_club_level_up.class);
            server_club_like server_club_like = generator.nextObject(server_club_like.class);
            server_club_list server_club_list = generator.nextObject(server_club_list.class);
            server_club_request server_club_request = generator.nextObject(server_club_request.class);
            server_club_user_join server_club_user_join = generator.nextObject(server_club_user_join.class);
            server_club_user_leave server_club_user_leave = generator.nextObject(server_club_user_leave.class);
            server_club_user_promotion server_club_user_promotion = generator
                    .nextObject(server_club_user_promotion.class);
            server_club_user_remove server_club_user_remove = generator.nextObject(server_club_user_remove.class);
            server_big_win server_big_win = generator.nextObject(server_big_win.class);
            server_bonus server_bonus = generator.nextObject(server_bonus.class);
            server_deal server_deal = generator.nextObject(server_deal.class);
            server_draw server_draw = generator.nextObject(server_draw.class);
            server_freespin_trigger server_freespin_trigger = generator.nextObject(server_freespin_trigger.class);
            server_gamble server_gamble = generator.nextObject(server_gamble.class);
            server_slot_unlock server_slot_unlock = generator.nextObject(server_slot_unlock.class);
            server_spin server_spin = generator.nextObject(server_spin.class);
            server_jackpot_claim server_jackpot_claim = generator.nextObject(server_jackpot_claim.class);
            server_jackpot_compensation server_jackpot_compensation = generator
                    .nextObject(server_jackpot_compensation.class);
            server_jackpot_reel server_jackpot_reel = generator.nextObject(server_jackpot_reel.class);
            server_collecting_game_pack_open server_collecting_game_pack_open = generator
                    .nextObject(server_collecting_game_pack_open.class);
            server_collecting_game_piece_convert server_collecting_game_piece_convert = generator
                    .nextObject(server_collecting_game_piece_convert.class);
            server_collecting_game_win server_collecting_game_win = generator
                    .nextObject(server_collecting_game_win.class);
            server_epic_album_earn_card server_epic_album_earn_card = generator
                    .nextObject(server_epic_album_earn_card.class);
            server_meta_game_gifts server_meta_game_gifts = generator.nextObject(server_meta_game_gifts.class);
            server_meta_game_requests server_meta_game_requests = generator.nextObject(server_meta_game_requests.class);
            server_scratch server_scratch = generator.nextObject(server_scratch.class);
            server_buy_bonus_bbb server_buy_bonus_bbb = generator.nextObject(server_buy_bonus_bbb.class);
            server_buy_bonus_compensation server_buy_bonus_compensation = generator
                    .nextObject(server_buy_bonus_compensation.class);
            server_challenge server_challenge = generator.nextObject(server_challenge.class);
            server_challenge_issue server_challenge_issue = generator.nextObject(server_challenge_issue.class);
            server_challenge_start server_challenge_start = generator.nextObject(server_challenge_start.class);
            server_chat server_chat = generator.nextObject(server_chat.class);
            server_chat_report server_chat_report = generator.nextObject(server_chat_report.class);
            server_click_fb_share server_click_fb_share = generator.nextObject(server_click_fb_share.class);
            server_collect_all server_collect_all = generator.nextObject(server_collect_all.class);
            server_compensation server_compensation = generator.nextObject(server_compensation.class);
            server_concurrent server_concurrent = generator.nextObject(server_concurrent.class);
            server_daily_wheel server_daily_wheel = generator.nextObject(server_daily_wheel.class);
            server_device_id_to_onesignal_id server_device_id_to_onesignal_id = generator
                    .nextObject(server_device_id_to_onesignal_id.class);
            server_email_connect server_email_connect = generator.nextObject(server_email_connect.class);
            server_fb_connect server_fb_connect = generator.nextObject(server_fb_connect.class);
            server_fb_friend server_fb_friend = generator.nextObject(server_fb_friend.class);
            server_friend_delete server_friend_delete = generator.nextObject(server_friend_delete.class);
            server_friend_request_accept server_friend_request_accept = generator
                    .nextObject(server_friend_request_accept.class);
            server_friend_request_reject server_friend_request_reject = generator
                    .nextObject(server_friend_request_reject.class);
            server_friend_request_send server_friend_request_send = generator
                    .nextObject(server_friend_request_send.class);
            server_gem_purchase server_gem_purchase = generator.nextObject(server_gem_purchase.class);
            server_inbox_reward server_inbox_reward = generator.nextObject(server_inbox_reward.class);
            server_kudo_reward server_kudo_reward = generator.nextObject(server_kudo_reward.class);
            server_login server_login = generator.nextObject(server_login.class);
            server_lucky_five_win server_lucky_five_win = generator.nextObject(server_lucky_five_win.class);
            server_lucky_spin server_lucky_spin = generator.nextObject(server_lucky_spin.class);
            server_mute server_mute = generator.nextObject(server_mute.class);
            server_new_user_auto_passive_event server_new_user_auto_passive_event = generator
                    .nextObject(server_new_user_auto_passive_event.class);
            server_onesignal_create_notification server_onesignal_create_notification = generator
                    .nextObject(server_onesignal_create_notification.class);
            server_programmed_win server_programmed_win = generator.nextObject(server_programmed_win.class);
            server_purchase server_purchase = generator.nextObject(server_purchase.class);
            server_purchased_mega_wheel server_purchased_mega_wheel = generator
                    .nextObject(server_purchased_mega_wheel.class);
            server_report server_report = generator.nextObject(server_report.class);
            server_reward server_reward = generator.nextObject(server_reward.class);
            server_smart_pot_of_gold server_smart_pot_of_gold = generator.nextObject(server_smart_pot_of_gold.class);
            server_survey_reward server_survey_reward = generator.nextObject(server_survey_reward.class);
            server_tier_match_offer_claim server_tier_match_offer_claim = generator
                    .nextObject(server_tier_match_offer_claim.class);
            server_tutorial_reward server_tutorial_reward = generator.nextObject(server_tutorial_reward.class);
            server_unexpected_error server_unexpected_error = generator.nextObject(server_unexpected_error.class);
            server_uninstall server_uninstall = generator.nextObject(server_uninstall.class);
            server_update_popup_force server_update_popup_force = generator.nextObject(server_update_popup_force.class);
            server_user_expired server_user_expired = generator.nextObject(server_user_expired.class);
            server_vip_deal server_vip_deal = generator.nextObject(server_vip_deal.class);
            server_vip_deal_redeem server_vip_deal_redeem = generator.nextObject(server_vip_deal_redeem.class);
            server_edit_profile server_edit_profile = generator.nextObject(server_edit_profile.class);
            server_epic_album_reward server_epic_album_reward = generator.nextObject(server_epic_album_reward.class);
            server_club_league_point server_club_league_point = generator.nextObject(server_club_league_point.class);
            server_send_social_credit server_send_social_credit = generator.nextObject(server_send_social_credit.class);
            server_gamble_result server_gamble_result = generator.nextObject(server_gamble_result.class);
            server_apple_connect server_apple_connect = generator.nextObject(server_apple_connect.class);
            server_canvas_click_app_icon server_canvas_click_app_icon = generator
                    .nextObject(server_canvas_click_app_icon.class);
            server_pre_bbb server_pre_bbb = generator.nextObject(server_pre_bbb.class);
            server_free_gem_booster server_free_gem_booster = generator.nextObject(server_free_gem_booster.class);
            server_season_pass_point_win server_season_pass_point_win = generator
                    .nextObject(server_season_pass_point_win.class);
            server_season_pass_reward server_season_pass_reward = generator.nextObject(server_season_pass_reward.class);
            server_announcement_setting server_announcement_setting = generator
                    .nextObject(server_announcement_setting.class);
            server_season_pass_level_up server_season_pass_level_up = generator
                    .nextObject(server_season_pass_level_up.class);
            server_gem_jackpot_spin server_gem_jackpot_spin = generator.nextObject(server_gem_jackpot_spin.class);
            server_season_pass_reset server_season_pass_reset = generator.nextObject(server_season_pass_reset.class);
            server_terms_of_use_agree server_terms_of_use_agree = generator.nextObject(server_terms_of_use_agree.class);
            server_boss_raiders_contribute server_boss_raiders_contribute = generator
                    .nextObject(server_boss_raiders_contribute.class);
            server_boss_raiders_spin server_boss_raiders_spin = generator.nextObject(server_boss_raiders_spin.class);
            server_boss_raiders_bonus server_boss_raiders_bonus = generator.nextObject(server_boss_raiders_bonus.class);
            server_club_boss_raiders_league_point server_club_boss_raiders_league_point = generator
                    .nextObject(server_club_boss_raiders_league_point.class);
            server_club_boss_raiders_result server_club_boss_raiders_result = generator
                    .nextObject(server_club_boss_raiders_result.class);
            server_inbox_reward_expired server_inbox_reward_expired = generator
                    .nextObject(server_inbox_reward_expired.class);
            server_lucky_spin_expired server_lucky_spin_expired = generator.nextObject(server_lucky_spin_expired.class);
            server_bonus_spin_expired server_bonus_spin_expired = generator.nextObject(server_bonus_spin_expired.class);
            server_play server_play = generator.nextObject(server_play.class);
            server_club_send_leader_push server_club_send_leader_push = generator
                    .nextObject(server_club_send_leader_push.class);
            server_leader_push_open server_leader_push_open = generator.nextObject(server_leader_push_open.class);
            server_get_spin_deal server_get_spin_deal = generator.nextObject(server_get_spin_deal.class);
            server_play_spin_deal server_play_spin_deal = generator.nextObject(server_play_spin_deal.class);
            server_club_arena_contribute server_club_arena_contribute = generator
                    .nextObject(server_club_arena_contribute.class);
            server_club_arena_match server_club_arena_match = generator.nextObject(server_club_arena_match.class);
            server_club_arena_spin server_club_arena_spin = generator.nextObject(server_club_arena_spin.class);
            server_system_chat server_system_chat = generator.nextObject(server_system_chat.class);
            server_hog_game_enter server_hog_game_enter = generator.nextObject(server_hog_game_enter.class);
            server_hog_result server_hog_result = generator.nextObject(server_hog_result.class);
            server_hog_unlock server_hog_unlock = generator.nextObject(server_hog_unlock.class);
            server_hog_contribute server_hog_contribute = generator.nextObject(server_hog_contribute.class);
            server_hog_deal_compensation server_hog_deal_compensation = generator
                    .nextObject(server_hog_deal_compensation.class);

            ProducerRecord<Long, server_canvas_click_bookmark> server_canvas_click_bookmark_record = new ProducerRecord<>(
                    topicName, id, server_canvas_click_bookmark);
            ProducerRecord<Long, server_canvas_device_info> server_canvas_device_info_record = new ProducerRecord<>(
                    topicName, id, server_canvas_device_info);
            ProducerRecord<Long, server_canvas_fb_auth> server_canvas_fb_auth_record = new ProducerRecord<>(topicName,
                    id, server_canvas_fb_auth);
            ProducerRecord<Long, server_canvas_login> server_canvas_login_record = new ProducerRecord<>(topicName, id,
                    server_canvas_login);
            ProducerRecord<Long, server_canvas_page_load> server_canvas_page_load_record = new ProducerRecord<>(
                    topicName, id, server_canvas_page_load);
            ProducerRecord<Long, server_club_challenge_mission_complete> server_club_challenge_mission_complete_record = new ProducerRecord<>(
                    topicName, id, server_club_challenge_mission_complete);
            ProducerRecord<Long, server_club_contribution> server_club_contribution_record = new ProducerRecord<>(
                    topicName, id, server_club_contribution);
            ProducerRecord<Long, server_club_create> server_club_create_record = new ProducerRecord<>(topicName, id,
                    server_club_create);
            ProducerRecord<Long, server_club_delete> server_club_delete_record = new ProducerRecord<>(topicName, id,
                    server_club_delete);
            ProducerRecord<Long, server_club_donation> server_club_donation_record = new ProducerRecord<>(topicName, id,
                    server_club_donation);
            ProducerRecord<Long, server_club_edit> server_club_edit_record = new ProducerRecord<>(topicName, id,
                    server_club_edit);
            ProducerRecord<Long, server_club_invite> server_club_invite_record = new ProducerRecord<>(topicName, id,
                    server_club_invite);
            ProducerRecord<Long, server_club_level_up> server_club_level_up_record = new ProducerRecord<>(topicName, id,
                    server_club_level_up);
            ProducerRecord<Long, server_club_like> server_club_like_record = new ProducerRecord<>(topicName, id,
                    server_club_like);
            ProducerRecord<Long, server_club_list> server_club_list_record = new ProducerRecord<>(topicName, id,
                    server_club_list);
            ProducerRecord<Long, server_club_request> server_club_request_record = new ProducerRecord<>(topicName, id,
                    server_club_request);
            ProducerRecord<Long, server_club_user_join> server_club_user_join_record = new ProducerRecord<>(topicName,
                    id, server_club_user_join);
            ProducerRecord<Long, server_club_user_leave> server_club_user_leave_record = new ProducerRecord<>(topicName,
                    id, server_club_user_leave);
            ProducerRecord<Long, server_club_user_promotion> server_club_user_promotion_record = new ProducerRecord<>(
                    topicName, id, server_club_user_promotion);
            ProducerRecord<Long, server_club_user_remove> server_club_user_remove_record = new ProducerRecord<>(
                    topicName, id, server_club_user_remove);
            ProducerRecord<Long, server_big_win> server_big_win_record = new ProducerRecord<>(topicName, id,
                    server_big_win);
            ProducerRecord<Long, server_bonus> server_bonus_record = new ProducerRecord<>(topicName, id, server_bonus);
            ProducerRecord<Long, server_deal> server_deal_record = new ProducerRecord<>(topicName, id, server_deal);
            ProducerRecord<Long, server_draw> server_draw_record = new ProducerRecord<>(topicName, id, server_draw);
            ProducerRecord<Long, server_freespin_trigger> server_freespin_trigger_record = new ProducerRecord<>(
                    topicName, id, server_freespin_trigger);
            ProducerRecord<Long, server_gamble> server_gamble_record = new ProducerRecord<>(topicName, id,
                    server_gamble);
            ProducerRecord<Long, server_slot_unlock> server_slot_unlock_record = new ProducerRecord<>(topicName, id,
                    server_slot_unlock);
            ProducerRecord<Long, server_spin> server_spin_record = new ProducerRecord<>(topicName, id, server_spin);
            ProducerRecord<Long, server_jackpot_claim> server_jackpot_claim_record = new ProducerRecord<>(topicName, id,
                    server_jackpot_claim);
            ProducerRecord<Long, server_jackpot_compensation> server_jackpot_compensation_record = new ProducerRecord<>(
                    topicName, id, server_jackpot_compensation);
            ProducerRecord<Long, server_jackpot_reel> server_jackpot_reel_record = new ProducerRecord<>(topicName, id,
                    server_jackpot_reel);
            ProducerRecord<Long, server_collecting_game_pack_open> server_collecting_game_pack_open_record = new ProducerRecord<>(
                    topicName, id, server_collecting_game_pack_open);
            ProducerRecord<Long, server_collecting_game_piece_convert> server_collecting_game_piece_convert_record = new ProducerRecord<>(
                    topicName, id, server_collecting_game_piece_convert);
            ProducerRecord<Long, server_collecting_game_win> server_collecting_game_win_record = new ProducerRecord<>(
                    topicName, id, server_collecting_game_win);
            ProducerRecord<Long, server_epic_album_earn_card> server_epic_album_earn_card_record = new ProducerRecord<>(
                    topicName, id, server_epic_album_earn_card);
            ProducerRecord<Long, server_meta_game_gifts> server_meta_game_gifts_record = new ProducerRecord<>(topicName,
                    id, server_meta_game_gifts);
            ProducerRecord<Long, server_meta_game_requests> server_meta_game_requests_record = new ProducerRecord<>(
                    topicName, id, server_meta_game_requests);
            ProducerRecord<Long, server_scratch> server_scratch_record = new ProducerRecord<>(topicName, id,
                    server_scratch);
            ProducerRecord<Long, server_buy_bonus_bbb> server_buy_bonus_bbb_record = new ProducerRecord<>(topicName, id,
                    server_buy_bonus_bbb);
            ProducerRecord<Long, server_buy_bonus_compensation> server_buy_bonus_compensation_record = new ProducerRecord<>(
                    topicName, id, server_buy_bonus_compensation);
            ProducerRecord<Long, server_challenge> server_challenge_record = new ProducerRecord<>(topicName, id,
                    server_challenge);
            ProducerRecord<Long, server_challenge_issue> server_challenge_issue_record = new ProducerRecord<>(topicName,
                    id, server_challenge_issue);
            ProducerRecord<Long, server_challenge_start> server_challenge_start_record = new ProducerRecord<>(topicName,
                    id, server_challenge_start);
            ProducerRecord<Long, server_chat> server_chat_record = new ProducerRecord<>(topicName, id, server_chat);
            ProducerRecord<Long, server_chat_report> server_chat_report_record = new ProducerRecord<>(topicName, id,
                    server_chat_report);
            ProducerRecord<Long, server_click_fb_share> server_click_fb_share_record = new ProducerRecord<>(topicName,
                    id, server_click_fb_share);
            ProducerRecord<Long, server_collect_all> server_collect_all_record = new ProducerRecord<>(topicName, id,
                    server_collect_all);
            ProducerRecord<Long, server_compensation> server_compensation_record = new ProducerRecord<>(topicName, id,
                    server_compensation);
            ProducerRecord<Long, server_concurrent> server_concurrent_record = new ProducerRecord<>(topicName, id,
                    server_concurrent);
            ProducerRecord<Long, server_daily_wheel> server_daily_wheel_record = new ProducerRecord<>(topicName, id,
                    server_daily_wheel);
            ProducerRecord<Long, server_device_id_to_onesignal_id> server_device_id_to_onesignal_id_record = new ProducerRecord<>(
                    topicName, id, server_device_id_to_onesignal_id);
            ProducerRecord<Long, server_email_connect> server_email_connect_record = new ProducerRecord<>(topicName, id,
                    server_email_connect);
            ProducerRecord<Long, server_fb_connect> server_fb_connect_record = new ProducerRecord<>(topicName, id,
                    server_fb_connect);
            ProducerRecord<Long, server_fb_friend> server_fb_friend_record = new ProducerRecord<>(topicName, id,
                    server_fb_friend);
            ProducerRecord<Long, server_friend_delete> server_friend_delete_record = new ProducerRecord<>(topicName, id,
                    server_friend_delete);
            ProducerRecord<Long, server_friend_request_accept> server_friend_request_accept_record = new ProducerRecord<>(
                    topicName, id, server_friend_request_accept);
            ProducerRecord<Long, server_friend_request_reject> server_friend_request_reject_record = new ProducerRecord<>(
                    topicName, id, server_friend_request_reject);
            ProducerRecord<Long, server_friend_request_send> server_friend_request_send_record = new ProducerRecord<>(
                    topicName, id, server_friend_request_send);
            ProducerRecord<Long, server_gem_purchase> server_gem_purchase_record = new ProducerRecord<>(topicName, id,
                    server_gem_purchase);
            ProducerRecord<Long, server_inbox_reward> server_inbox_reward_record = new ProducerRecord<>(topicName, id,
                    server_inbox_reward);
            ProducerRecord<Long, server_kudo_reward> server_kudo_reward_record = new ProducerRecord<>(topicName, id,
                    server_kudo_reward);
            ProducerRecord<Long, server_login> server_login_record = new ProducerRecord<>(topicName, id, server_login);
            ProducerRecord<Long, server_lucky_five_win> server_lucky_five_win_record = new ProducerRecord<>(topicName,
                    id, server_lucky_five_win);
            ProducerRecord<Long, server_lucky_spin> server_lucky_spin_record = new ProducerRecord<>(topicName, id,
                    server_lucky_spin);
            ProducerRecord<Long, server_mute> server_mute_record = new ProducerRecord<>(topicName, id, server_mute);
            ProducerRecord<Long, server_new_user_auto_passive_event> server_new_user_auto_passive_event_record = new ProducerRecord<>(
                    topicName, id, server_new_user_auto_passive_event);
            ProducerRecord<Long, server_onesignal_create_notification> server_onesignal_create_notification_record = new ProducerRecord<>(
                    topicName, id, server_onesignal_create_notification);
            ProducerRecord<Long, server_programmed_win> server_programmed_win_record = new ProducerRecord<>(topicName,
                    id, server_programmed_win);
            ProducerRecord<Long, server_purchase> server_purchase_record = new ProducerRecord<>(topicName, id,
                    server_purchase);
            ProducerRecord<Long, server_purchased_mega_wheel> server_purchased_mega_wheel_record = new ProducerRecord<>(
                    topicName, id, server_purchased_mega_wheel);
            ProducerRecord<Long, server_report> server_report_record = new ProducerRecord<>(topicName, id,
                    server_report);
            ProducerRecord<Long, server_reward> server_reward_record = new ProducerRecord<>(topicName, id,
                    server_reward);
            ProducerRecord<Long, server_smart_pot_of_gold> server_smart_pot_of_gold_record = new ProducerRecord<>(
                    topicName, id, server_smart_pot_of_gold);
            ProducerRecord<Long, server_survey_reward> server_survey_reward_record = new ProducerRecord<>(topicName, id,
                    server_survey_reward);
            ProducerRecord<Long, server_tier_match_offer_claim> server_tier_match_offer_claim_record = new ProducerRecord<>(
                    topicName, id, server_tier_match_offer_claim);
            ProducerRecord<Long, server_tutorial_reward> server_tutorial_reward_record = new ProducerRecord<>(topicName,
                    id, server_tutorial_reward);
            ProducerRecord<Long, server_unexpected_error> server_unexpected_error_record = new ProducerRecord<>(
                    topicName, id, server_unexpected_error);
            ProducerRecord<Long, server_uninstall> server_uninstall_record = new ProducerRecord<>(topicName, id,
                    server_uninstall);
            ProducerRecord<Long, server_update_popup_force> server_update_popup_force_record = new ProducerRecord<>(
                    topicName, id, server_update_popup_force);
            ProducerRecord<Long, server_user_expired> server_user_expired_record = new ProducerRecord<>(topicName, id,
                    server_user_expired);
            ProducerRecord<Long, server_vip_deal> server_vip_deal_record = new ProducerRecord<>(topicName, id,
                    server_vip_deal);
            ProducerRecord<Long, server_vip_deal_redeem> server_vip_deal_redeem_record = new ProducerRecord<>(topicName,
                    id, server_vip_deal_redeem);
            ProducerRecord<Long, server_edit_profile> server_edit_profile_record = new ProducerRecord<>(topicName, id,
                    server_edit_profile);
            ProducerRecord<Long, server_epic_album_reward> server_epic_album_reward_record = new ProducerRecord<>(
                    topicName, id, server_epic_album_reward);
            ProducerRecord<Long, server_club_league_point> server_club_league_point_record = new ProducerRecord<>(
                    topicName, id, server_club_league_point);
            ProducerRecord<Long, server_send_social_credit> server_send_social_credit_record = new ProducerRecord<>(
                    topicName, id, server_send_social_credit);
            ProducerRecord<Long, server_gamble_result> server_gamble_result_record = new ProducerRecord<>(topicName, id,
                    server_gamble_result);
            ProducerRecord<Long, server_apple_connect> server_apple_connect_record = new ProducerRecord<>(topicName, id,
                    server_apple_connect);
            ProducerRecord<Long, server_canvas_click_app_icon> server_canvas_click_app_icon_record = new ProducerRecord<>(
                    topicName, id, server_canvas_click_app_icon);
            ProducerRecord<Long, server_pre_bbb> server_pre_bbb_record = new ProducerRecord<>(topicName, id,
                    server_pre_bbb);
            ProducerRecord<Long, server_free_gem_booster> server_free_gem_booster_record = new ProducerRecord<>(
                    topicName, id, server_free_gem_booster);
            ProducerRecord<Long, server_season_pass_point_win> server_season_pass_point_win_record = new ProducerRecord<>(
                    topicName, id, server_season_pass_point_win);
            ProducerRecord<Long, server_season_pass_reward> server_season_pass_reward_record = new ProducerRecord<>(
                    topicName, id, server_season_pass_reward);
            ProducerRecord<Long, server_announcement_setting> server_announcement_setting_record = new ProducerRecord<>(
                    topicName, id, server_announcement_setting);
            ProducerRecord<Long, server_season_pass_level_up> server_season_pass_level_up_record = new ProducerRecord<>(
                    topicName, id, server_season_pass_level_up);
            ProducerRecord<Long, server_gem_jackpot_spin> server_gem_jackpot_spin_record = new ProducerRecord<>(
                    topicName, id, server_gem_jackpot_spin);
            ProducerRecord<Long, server_season_pass_reset> server_season_pass_reset_record = new ProducerRecord<>(
                    topicName, id, server_season_pass_reset);
            ProducerRecord<Long, server_terms_of_use_agree> server_terms_of_use_agree_record = new ProducerRecord<>(
                    topicName, id, server_terms_of_use_agree);
            ProducerRecord<Long, server_boss_raiders_contribute> server_boss_raiders_contribute_record = new ProducerRecord<>(
                    topicName, id, server_boss_raiders_contribute);
            ProducerRecord<Long, server_boss_raiders_spin> server_boss_raiders_spin_record = new ProducerRecord<>(
                    topicName, id, server_boss_raiders_spin);
            ProducerRecord<Long, server_boss_raiders_bonus> server_boss_raiders_bonus_record = new ProducerRecord<>(
                    topicName, id, server_boss_raiders_bonus);
            ProducerRecord<Long, server_club_boss_raiders_league_point> server_club_boss_raiders_league_point_record = new ProducerRecord<>(
                    topicName, id, server_club_boss_raiders_league_point);
            ProducerRecord<Long, server_club_boss_raiders_result> server_club_boss_raiders_result_record = new ProducerRecord<>(
                    topicName, id, server_club_boss_raiders_result);
            ProducerRecord<Long, server_inbox_reward_expired> server_inbox_reward_expired_record = new ProducerRecord<>(
                    topicName, id, server_inbox_reward_expired);
            ProducerRecord<Long, server_lucky_spin_expired> server_lucky_spin_expired_record = new ProducerRecord<>(
                    topicName, id, server_lucky_spin_expired);
            ProducerRecord<Long, server_bonus_spin_expired> server_bonus_spin_expired_record = new ProducerRecord<>(
                    topicName, id, server_bonus_spin_expired);
            ProducerRecord<Long, server_play> server_play_record = new ProducerRecord<>(topicName, id, server_play);
            ProducerRecord<Long, server_club_send_leader_push> server_club_send_leader_push_record = new ProducerRecord<>(
                    topicName, id, server_club_send_leader_push);
            ProducerRecord<Long, server_leader_push_open> server_leader_push_open_record = new ProducerRecord<>(
                    topicName, id, server_leader_push_open);
            ProducerRecord<Long, server_get_spin_deal> server_get_spin_deal_record = new ProducerRecord<>(topicName, id,
                    server_get_spin_deal);
            ProducerRecord<Long, server_play_spin_deal> server_play_spin_deal_record = new ProducerRecord<>(topicName,
                    id, server_play_spin_deal);
            ProducerRecord<Long, server_club_arena_contribute> server_club_arena_contribute_record = new ProducerRecord<>(
                    topicName, id, server_club_arena_contribute);
            ProducerRecord<Long, server_club_arena_match> server_club_arena_match_record = new ProducerRecord<>(
                    topicName, id, server_club_arena_match);
            ProducerRecord<Long, server_club_arena_spin> server_club_arena_spin_record = new ProducerRecord<>(topicName,
                    id, server_club_arena_spin);
            ProducerRecord<Long, server_system_chat> server_system_chat_record = new ProducerRecord<>(topicName, id,
                    server_system_chat);
            ProducerRecord<Long, server_hog_game_enter> server_hog_game_enter_record = new ProducerRecord<>(topicName,
                    id, server_hog_game_enter);
            ProducerRecord<Long, server_hog_result> server_hog_result_record = new ProducerRecord<>(topicName, id,
                    server_hog_result);
            ProducerRecord<Long, server_hog_unlock> server_hog_unlock_record = new ProducerRecord<>(topicName, id,
                    server_hog_unlock);
            ProducerRecord<Long, server_hog_contribute> server_hog_contribute_record = new ProducerRecord<>(topicName,
                    id, server_hog_contribute);
            ProducerRecord<Long, server_hog_deal_compensation> server_hog_deal_compensation_record = new ProducerRecord<>(
                    topicName, id, server_hog_deal_compensation);

            server_canvas_click_bookmark_producer.send(server_canvas_click_bookmark_record);
            server_canvas_device_info_producer.send(server_canvas_device_info_record);
            server_canvas_fb_auth_producer.send(server_canvas_fb_auth_record);
            server_canvas_login_producer.send(server_canvas_login_record);
            server_canvas_page_load_producer.send(server_canvas_page_load_record);
            server_club_challenge_mission_complete_producer.send(server_club_challenge_mission_complete_record);
            server_club_contribution_producer.send(server_club_contribution_record);
            server_club_create_producer.send(server_club_create_record);
            server_club_delete_producer.send(server_club_delete_record);
            server_club_donation_producer.send(server_club_donation_record);
            server_club_edit_producer.send(server_club_edit_record);
            server_club_invite_producer.send(server_club_invite_record);
            server_club_level_up_producer.send(server_club_level_up_record);
            server_club_like_producer.send(server_club_like_record);
            server_club_list_producer.send(server_club_list_record);
            server_club_request_producer.send(server_club_request_record);
            server_club_user_join_producer.send(server_club_user_join_record);
            server_club_user_leave_producer.send(server_club_user_leave_record);
            server_club_user_promotion_producer.send(server_club_user_promotion_record);
            server_club_user_remove_producer.send(server_club_user_remove_record);
            server_big_win_producer.send(server_big_win_record);
            server_bonus_producer.send(server_bonus_record);
            server_deal_producer.send(server_deal_record);
            server_draw_producer.send(server_draw_record);
            server_freespin_trigger_producer.send(server_freespin_trigger_record);
            server_gamble_producer.send(server_gamble_record);
            server_slot_unlock_producer.send(server_slot_unlock_record);
            server_spin_producer.send(server_spin_record);
            server_jackpot_claim_producer.send(server_jackpot_claim_record);
            server_jackpot_compensation_producer.send(server_jackpot_compensation_record);
            server_jackpot_reel_producer.send(server_jackpot_reel_record);
            server_collecting_game_pack_open_producer.send(server_collecting_game_pack_open_record);
            server_collecting_game_piece_convert_producer.send(server_collecting_game_piece_convert_record);
            server_collecting_game_win_producer.send(server_collecting_game_win_record);
            server_epic_album_earn_card_producer.send(server_epic_album_earn_card_record);
            server_meta_game_gifts_producer.send(server_meta_game_gifts_record);
            server_meta_game_requests_producer.send(server_meta_game_requests_record);
            server_scratch_producer.send(server_scratch_record);
            server_buy_bonus_bbb_producer.send(server_buy_bonus_bbb_record);
            server_buy_bonus_compensation_producer.send(server_buy_bonus_compensation_record);
            server_challenge_producer.send(server_challenge_record);
            server_challenge_issue_producer.send(server_challenge_issue_record);
            server_challenge_start_producer.send(server_challenge_start_record);
            server_chat_producer.send(server_chat_record);
            server_chat_report_producer.send(server_chat_report_record);
            server_click_fb_share_producer.send(server_click_fb_share_record);
            server_collect_all_producer.send(server_collect_all_record);
            server_compensation_producer.send(server_compensation_record);
            server_concurrent_producer.send(server_concurrent_record);
            server_daily_wheel_producer.send(server_daily_wheel_record);
            server_device_id_to_onesignal_id_producer.send(server_device_id_to_onesignal_id_record);
            server_email_connect_producer.send(server_email_connect_record);
            server_fb_connect_producer.send(server_fb_connect_record);
            server_fb_friend_producer.send(server_fb_friend_record);
            server_friend_delete_producer.send(server_friend_delete_record);
            server_friend_request_accept_producer.send(server_friend_request_accept_record);
            server_friend_request_reject_producer.send(server_friend_request_reject_record);
            server_friend_request_send_producer.send(server_friend_request_send_record);
            server_gem_purchase_producer.send(server_gem_purchase_record);
            server_inbox_reward_producer.send(server_inbox_reward_record);
            server_kudo_reward_producer.send(server_kudo_reward_record);
            server_login_producer.send(server_login_record);
            server_lucky_five_win_producer.send(server_lucky_five_win_record);
            server_lucky_spin_producer.send(server_lucky_spin_record);
            server_mute_producer.send(server_mute_record);
            server_new_user_auto_passive_event_producer.send(server_new_user_auto_passive_event_record);
            server_onesignal_create_notification_producer.send(server_onesignal_create_notification_record);
            server_programmed_win_producer.send(server_programmed_win_record);
            server_purchase_producer.send(server_purchase_record);
            server_purchased_mega_wheel_producer.send(server_purchased_mega_wheel_record);
            server_report_producer.send(server_report_record);
            server_reward_producer.send(server_reward_record);
            server_smart_pot_of_gold_producer.send(server_smart_pot_of_gold_record);
            server_survey_reward_producer.send(server_survey_reward_record);
            server_tier_match_offer_claim_producer.send(server_tier_match_offer_claim_record);
            server_tutorial_reward_producer.send(server_tutorial_reward_record);
            server_unexpected_error_producer.send(server_unexpected_error_record);
            server_uninstall_producer.send(server_uninstall_record);
            server_update_popup_force_producer.send(server_update_popup_force_record);
            server_user_expired_producer.send(server_user_expired_record);
            server_vip_deal_producer.send(server_vip_deal_record);
            server_vip_deal_redeem_producer.send(server_vip_deal_redeem_record);
            server_edit_profile_producer.send(server_edit_profile_record);
            server_epic_album_reward_producer.send(server_epic_album_reward_record);
            server_club_league_point_producer.send(server_club_league_point_record);
            server_send_social_credit_producer.send(server_send_social_credit_record);
            server_gamble_result_producer.send(server_gamble_result_record);
            server_apple_connect_producer.send(server_apple_connect_record);
            server_canvas_click_app_icon_producer.send(server_canvas_click_app_icon_record);
            server_pre_bbb_producer.send(server_pre_bbb_record);
            server_free_gem_booster_producer.send(server_free_gem_booster_record);
            server_season_pass_point_win_producer.send(server_season_pass_point_win_record);
            server_season_pass_reward_producer.send(server_season_pass_reward_record);
            server_announcement_setting_producer.send(server_announcement_setting_record);
            server_season_pass_level_up_producer.send(server_season_pass_level_up_record);
            server_gem_jackpot_spin_producer.send(server_gem_jackpot_spin_record);
            server_season_pass_reset_producer.send(server_season_pass_reset_record);
            server_terms_of_use_agree_producer.send(server_terms_of_use_agree_record);
            server_boss_raiders_contribute_producer.send(server_boss_raiders_contribute_record);
            server_boss_raiders_spin_producer.send(server_boss_raiders_spin_record);
            server_boss_raiders_bonus_producer.send(server_boss_raiders_bonus_record);
            server_club_boss_raiders_league_point_producer.send(server_club_boss_raiders_league_point_record);
            server_club_boss_raiders_result_producer.send(server_club_boss_raiders_result_record);
            server_inbox_reward_expired_producer.send(server_inbox_reward_expired_record);
            server_lucky_spin_expired_producer.send(server_lucky_spin_expired_record);
            server_bonus_spin_expired_producer.send(server_bonus_spin_expired_record);
            server_play_producer.send(server_play_record);
            server_club_send_leader_push_producer.send(server_club_send_leader_push_record);
            server_leader_push_open_producer.send(server_leader_push_open_record);
            server_get_spin_deal_producer.send(server_get_spin_deal_record);
            server_play_spin_deal_producer.send(server_play_spin_deal_record);
            server_club_arena_contribute_producer.send(server_club_arena_contribute_record);
            server_club_arena_match_producer.send(server_club_arena_match_record);
            server_club_arena_spin_producer.send(server_club_arena_spin_record);
            server_system_chat_producer.send(server_system_chat_record);
            server_hog_game_enter_producer.send(server_hog_game_enter_record);
            server_hog_result_producer.send(server_hog_result_record);
            server_hog_unlock_producer.send(server_hog_unlock_record);
            server_hog_contribute_producer.send(server_hog_contribute_record);
            server_hog_deal_compensation_producer.send(server_hog_deal_compensation_record);

            id++;
            TimeUnit.MILLISECONDS.sleep(messageBackOff);
        }
    }

    private Map<String, String> defaultProps = Map.of(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker:9092",
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.LongSerializer",
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroSerializer",
            AbstractKafkaAvroSerDeConfig.AUTO_REGISTER_SCHEMAS, "false",
            AbstractKafkaAvroSerDeConfig.USE_LATEST_VERSION, "true");

    private Properties buildProperties(Map<String, String> baseProps, Map<String, String> envProps, String prefix) {
        Map<String, String> systemProperties = envProps.entrySet()
                .stream()
                .filter(e -> e.getKey().startsWith(prefix))
                .filter(e -> !e.getValue().isEmpty())
                .collect(Collectors.toMap(
                        e -> e.getKey()
                                .replace(prefix, "")
                                .toLowerCase()
                                .replace("_", "."),
                        e -> e.getValue()));

        Properties props = new Properties();
        props.putAll(baseProps);
        props.putAll(systemProperties);
        return props;
    }

    private void createTopic(AdminClient adminClient, String topicName, Integer numberOfPartitions,
            Short replicationFactor) throws InterruptedException, ExecutionException {
        if (!adminClient.listTopics().names().get().contains(topicName)) {
            logger.info("Creating topic {}", topicName);

            final Map<String, String> configs = replicationFactor < 3
                    ? Map.of(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, "1")
                    : Map.of();

            final NewTopic newTopic = new NewTopic(topicName, numberOfPartitions, replicationFactor);
            newTopic.configs(configs);
            try {
                CreateTopicsResult topicsCreationResult = adminClient.createTopics(Collections.singleton(newTopic));
                topicsCreationResult.all().get();
            } catch (ExecutionException e) {
                // silent ignore if topic already exists
            }
        }
    }}

