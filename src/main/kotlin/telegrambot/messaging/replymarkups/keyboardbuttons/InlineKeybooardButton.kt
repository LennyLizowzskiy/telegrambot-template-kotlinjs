package telegrambot.messaging.replymarkups.keyboardbuttons

import kotlinx.serialization.Serializable
import telegrambot.other.LoginUrl
import telegrambot.other.WebAppInfo

@Serializable
data class InlineKeyboardButton(
    val text: String,
    val url: String?,
    val callback_data: String?,
    val web_app: WebAppInfo,
    val login_url: LoginUrl,
    val switch_inline_query: String?,
    val switch_inline_query_current_chat: String?,
    val callback_game: Int? = null,
    val pay: Boolean?
)