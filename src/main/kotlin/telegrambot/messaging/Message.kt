package telegrambot.messaging

import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

external interface Message {
    @JsName("abc")
    val chat_id: String
    val text: String
    val parse_mode: String?
    val entities: Array<telegrambot.messaging.MessageEntity>?
    val disable_web_page_preview: Boolean?
    val disable_notification: Boolean?
    val protect_content: Boolean?
    val reply_to_message_id: Int?
    val allow_sending_without_reply: Boolean?
    val reply_markup: InlineKeyboardMarkup?
}