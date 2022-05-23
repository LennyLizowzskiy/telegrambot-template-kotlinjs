package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import resources.ResourceStore
import telegrambot.messaging.inlineresults.inputmessagecontents.InputMessageContent
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultGif(
    val type: String = "gif",
    override var id: String? = null,
    val gif_url: String,
    val gif_width: Int? = null,
    val gif_height: Int? = null,
    val gif_duration: Int? = null,
    val thumb_url: String,
    val thumb_mime_type: String? = null,
    val title: String? = null,
    val caption: String? = null,
    val parse_mode: String = ResourceStore.telegramBotDefaults["parse_mode"] as String,
    val caption_entities: Array<telegrambot.messaging.MessageEntity>? = null,
    val input_message_content: InputMessageContent? = null,
    val reply_markup: InlineKeyboardMarkup? = null
) : InlineQueryResultItem