package telegrambot.messaging.replymarkups

import kotlinx.serialization.Serializable
import telegrambot.messaging.replymarkups.keyboardbuttons.InlineKeyboardButton

@Serializable
data class InlineKeyboardMarkup(
    val inline_keyboard: Array<Array<InlineKeyboardButton>>,
    val resize_keyboard: Boolean = false,
    val one_time_keyboard: Boolean = false,
    val input_field_placeholder: String? = null,
    val selective: Boolean = false
) : ReplyMarkup()