package telegrambot.messaging.replymarkups.keyboardbuttons

import kotlinx.serialization.Serializable
import telegrambot.other.WebAppInfo

@Serializable
data class KeyboardButton(
    val text: String,
    val request_contact: Boolean = false,
    val request_location: Boolean = false,
    val request_poll: KeyboardButtonPollType? = null,
    val web_app: WebAppInfo? = null
)