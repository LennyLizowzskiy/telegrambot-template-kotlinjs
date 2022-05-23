package telegrambot.messaging.replymarkups.keyboardbuttons

import kotlinx.serialization.Serializable

@Serializable
data class KeyboardButtonPollType(
    val type: String? = null
)