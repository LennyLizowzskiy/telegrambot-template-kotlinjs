package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import telegrambot.messaging.inlineresults.inputmessagecontents.InputTextMessageContent
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultArticle(
    val type: String = "article",
    override var id: String? = null,
    val title: String,
    val input_message_content: InputTextMessageContent,
    val url: String? = null,
    val hide_url: Boolean = false,
    val description: String? = null,
    val reply_markup: InlineKeyboardMarkup? = null,
    val thumb_url: String? = null,
    val thumb_width: Int? = null,
    val thumb_height: Int? = null
) : InlineQueryResultItem