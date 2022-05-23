package telegrambot.messaging.response.chat

import telegrambot.messaging.response.inline.InlineQuerySender

external interface SimpleMessage {
    val message_id: Int
    val from: InlineQuerySender
    val chat: MessageSenderChat
    val date: Long
    val text: String
    val entities: Array<dynamic>
}