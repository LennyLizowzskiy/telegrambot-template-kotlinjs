package telegrambot.messaging.response.inline

external interface InlineQuery {
    val id: Long
    val from: InlineQuerySender
    val chat_type: String
    val query: String
    val offset: String
}