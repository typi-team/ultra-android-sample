package com.ultra.sample.ultra.delegates

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.typi.ultra.integration.localise.UltraLocalise
import com.typi.ultra.integration.localise.UltraLocaliseDelegate
import com.typi.ultra.integration.localise.model.Attachment
import com.typi.ultra.integration.localise.model.Call
import com.typi.ultra.integration.localise.model.ChatDetail
import com.typi.ultra.integration.localise.model.Chats
import com.typi.ultra.integration.localise.model.Common
import com.typi.ultra.integration.localise.model.Complaint
import com.typi.ultra.integration.localise.model.Connection
import com.typi.ultra.integration.localise.model.Date
import com.typi.ultra.integration.localise.model.Error
import com.typi.ultra.integration.localise.model.LastMessage
import com.typi.ultra.integration.localise.model.LastMessageCall
import com.typi.ultra.integration.localise.model.NoMessages
import com.typi.ultra.integration.localise.model.NotificationChannel
import com.typi.ultra.integration.localise.model.Updates
import com.ultra.sample.R

class UltraLocaliseDelegateImpl : UltraLocaliseDelegate {

    private val _localise = mutableStateOf(
        UltraLocalise(
            common = Common(
                yes = R.string.chat_common_yes,
                cancel = R.string.chat_common_cancel,
                ok = R.string.chat_common_ok,
                close = R.string.chat_common_close,
                copiedToClipboard = R.string.chat_common_copied_to_clipboard,
                agree = R.string.chat_common_agree,
                you = R.string.chat_common_you,
            ),
            error = Error(
                default = R.string.chat_error_default,
                appNotFound = R.string.chat_error_app_not_found,
                recordAudioMessage = R.string.chat_error_record_audio_message,
                disclaimerNotAccepted = R.string.chat_error_disclaimer_not_accepted,
            ),
            chats = Chats(
                title = R.string.chat_chats_title,
                deleteChatTitle = R.string.chat_chats_delete_chat_title,
                deleteChatDescription = R.string.chat_chats_delete_chat_description,
                lastMessage = LastMessage(
                    audio = R.string.chat_chats_last_message_audio,
                    voice = R.string.chat_chats_last_message_voice,
                    image = R.string.chat_chats_last_message_image,
                    video = R.string.chat_chats_last_message_video,
                    file = R.string.chat_chats_last_message_file,
                    money = R.string.chat_chats_last_message_money,
                    contact = R.string.chat_chats_last_message_contact,
                    location = R.string.chat_chats_last_message_location,
                    call = LastMessageCall(
                        incoming = R.string.chat_chats_last_message_call_incoming,
                        outgoing = R.string.chat_chats_last_message_call_outgoing,
                        cancelled = R.string.chat_chats_last_message_call_cancelled,
                        missed = R.string.chat_chats_last_message_call_missed,
                    ),
                ),
                noMessages = NoMessages(
                    p2p = R.string.chat_chats_no_messages_p2p,
                    personalManager = R.string.chat_chats_no_messages_personal_manager,
                    support = R.string.chat_chats_no_messages_support,
                    assistant = R.string.chat_chats_no_messages_assistant,
                ),
            ),
            updates = Updates(
                typing = R.string.chat_updates_typing,
                onlineStatus = R.string.chat_updates_online_status,
            ),
            connection = Connection(
                connecting = R.string.chat_connection_connecting,
                waiting = R.string.chat_connection_waiting,
            ),
            attachment = Attachment(
                add = R.string.chat_attachment_add,
                image = R.string.chat_attachment_image,
                video = R.string.chat_attachment_video,
                gallery = R.string.chat_attachment_gallery,
                contact = R.string.chat_attachment_contact,
                location = R.string.chat_attachment_location,
                document = R.string.chat_attachment_document,
            ),
            complaint = Complaint(
                enterComment = R.string.chat_complaint_enter_comment,
                comment = R.string.chat_complaint_comment,
                title = R.string.chat_complaint_title,
                spam = R.string.chat_complaint_spam,
                personalData = R.string.chat_complaint_personal_data,
                fraud = R.string.chat_complaint_fraud,
                serviceImposition = R.string.chat_complaint_service_imposition,
                abuse = R.string.chat_complaint_abuse,
                other = R.string.chat_complaint_other,
                accepted = R.string.chat_complaint_accepted,
            ),
            chatDetail = ChatDetail(
                withAssistant = R.string.chat_chat_detail_with_assistant,
                withSupport = R.string.chat_chat_detail_with_support,
                block = R.string.chat_chat_detail_block,
                unblock = R.string.chat_chat_detail_unblock,
                money = R.string.chat_chat_detail_money,
                transfer = R.string.chat_chat_detail_transfer,
                incomingCall = R.string.chat_chat_detail_incoming_call,
                outgoingCall = R.string.chat_chat_detail_outgoing_call,
                cancelledCall = R.string.chat_chat_detail_cancelled_call,
                missedCall = R.string.chat_chat_detail_missed_call,
                noAnswer = R.string.chat_chat_detail_no_answer,
                enterText = R.string.chat_chat_detail_enter_text,
                deleteMessagesTitle = R.string.chat_chat_detail_delete_messages_title,
                deleteMessageAction = R.string.chat_chat_detail_delete_messages_action,
                deleteMessagesConfirmation = R.string.chat_chat_detail_delete_messages_confirmation,
                deleteMessagesForEveryone = R.string.chat_chat_detail_delete_messages_for_everyone,
                disclaimerDescription = R.string.chat_chat_detail_disclaimer_description,
            ),
            notificationChannel = NotificationChannel(
                general = R.string.chat_notification_channel_general,
                incomingCalls = R.string.chat_notification_channel_incoming_calls,
                ongoingCalls = R.string.chat_notification_channel_ongoing_calls
            ),
            call = Call(
                incoming = R.string.chat_call_incoming,
                videoIncoming = R.string.chat_call_video_incoming,
                ongoing = R.string.chat_call_ongoing,
                answer = R.string.chat_call_answer,
                decline = R.string.chat_call_decline,
                statusEnded = R.string.chat_call_status_ended,
                statusRejected = R.string.chat_call_status_rejected,
                statusConnecting = R.string.chat_call_status_connecting,
                statusReconnecting = R.string.chat_call_status_reconnecting,
            ),
            date = Date(
                yesterday = R.string.chat_date_yesterday,
                now = R.string.chat_date_now,
                longTimeAgo = R.string.chat_date_long_time_ago,
                monday = R.string.chat_date_monday,
                tuesday = R.string.chat_date_tuesday,
                wednesday = R.string.chat_date_wednesday,
                thursday = R.string.chat_date_thursday,
                friday = R.string.chat_date_friday,
                saturday = R.string.chat_date_saturday,
                sunday = R.string.chat_date_sunday,
                minutes = R.plurals.chat_minutes_ago,
                hours = R.plurals.chat_hours_ago,
                days = R.plurals.chat_days_ago,
                months = R.plurals.chat_months_ago,
            )
        )
    )
    override val localise: State<UltraLocalise> = _localise
}