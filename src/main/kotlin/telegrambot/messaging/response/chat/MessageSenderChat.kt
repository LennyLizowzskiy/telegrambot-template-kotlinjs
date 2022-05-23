package telegrambot.messaging.response.chat

external interface MessageSenderChat {
    val id: Long
    val title: String?
    val first_name: String?
    val last_name: String?
    val username: String?
    val type: String
}