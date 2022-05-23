package telegrambot.messaging.replymarkups

import kotlinx.serialization.Serializable
import telegrambot.messaging.replymarkups.keyboardbuttons.KeyboardButton

@Serializable
data class ReplyKeyboardMarkup(
    val keyboard: Array<Array<KeyboardButton>>
) : ReplyMarkup()