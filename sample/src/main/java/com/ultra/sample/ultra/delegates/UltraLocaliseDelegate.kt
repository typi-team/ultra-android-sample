package com.ultra.sample.ultra.delegates

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.typi.ultra.integration.localise.DefaultUltraLocalise
import com.typi.ultra.integration.localise.UltraLocalise
import com.typi.ultra.integration.localise.UltraLocaliseDelegate
import com.typi.ultra.integration.localise.model.Attachment
import com.typi.ultra.integration.localise.model.Call
import com.typi.ultra.integration.localise.model.ChatDetail
import com.typi.ultra.integration.localise.model.Chats
import com.typi.ultra.integration.localise.model.Common
import com.typi.ultra.integration.localise.model.Complaint
import com.typi.ultra.integration.localise.model.Connection
import com.typi.ultra.integration.localise.model.Error
import com.typi.ultra.integration.localise.model.NotificationChannel
import com.typi.ultra.integration.localise.model.Updates
import com.ultra.sample.R

class UltraLocaliseDelegateImpl : UltraLocaliseDelegate {

    private val _localise = mutableStateOf(
        DefaultUltraLocalise(
            common = Common(
                yes = R.string.chat_common_yes,
                cancel = R.string.chat_common_cancel,
                ok = R.string.chat_common_ok,
                close = R.string.chat_common_close,
                copiedToClipboard = R.string.chat_common_copied_to_clipboard,
                agree = R.string.chat_common_agree,
            ),
            error = Error(
                default = R.string.chat_error_default,
                appNotFound = R.string.chat_error_app_not_found,
                recordAudioMessage = R.string.chat_error_record_audio_message,
            ),
            chats = Chats(
                title = R.string.chat_chats_title,
                lastAudioMessage = R.string.chat_chats_last_message_audio,
                lastVoiceMessage = R.string.chat_chats_last_message_voice,
                lastImageMessage = R.string.chat_chats_last_message_image,
                lastVideoMessage = R.string.chat_chats_last_message_video,
                lastFileMessage = R.string.chat_chats_last_message_file,
                lastMoneyMessage = R.string.chat_chats_last_message_money,
                lastContactMessage = R.string.chat_chats_last_message_contact,
                lastLocationMessage = R.string.chat_chats_last_message_location,
                noMessages = R.string.chat_chats_no_messages,
                deleteChatTitle = R.string.chat_chats_delete_chat_title,
                deleteChatDescription = R.string.chat_chats_delete_chat_description,
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
        )
    )
    override val localise: State<UltraLocalise> = _localise
}