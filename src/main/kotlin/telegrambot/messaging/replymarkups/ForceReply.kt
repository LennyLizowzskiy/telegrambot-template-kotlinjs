package telegrambot.messaging.replymarkups

data class ForceReply(
    val force_reply: Boolean = true,
    val input_field_placeholder: String? = null,
    val selective: Boolean = false
) : ReplyMarkup()