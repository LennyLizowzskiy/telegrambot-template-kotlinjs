package telegrambot.messaging.replymarkups

import kotlinx.serialization.Serializable

@Serializable
data class ReplyKeyboardRemove(
    val remove_keyboard: Boolean = true,
    val selective: Boolean = false
) : ReplyMarkup()