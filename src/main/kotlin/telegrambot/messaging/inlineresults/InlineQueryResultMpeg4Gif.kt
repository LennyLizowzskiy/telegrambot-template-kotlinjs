package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import resources.ResourceStore
import telegrambot.messaging.MessageEntity
import telegrambot.messaging.inlineresults.inputmessagecontents.InputMessageContent
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultMpeg4Gif(
    val type: String = "mpeg4_gif",
    override var id: String? = null,
    val mpeg4_url: String,
    val mpeg4_width: Int? = null,
    val mpeg4_height: Int? = null,
    val mpeg4_duration: Int? = null,
    val thumb_url: String,
    val thumb_mime_type: String? = null,
    val title: String? = null,
    val caption: String? = null,
    val parse_mode: String = ResourceStore.telegramBotDefaults["parse_mode"] as String,
    val caption_entities: Array<MessageEntity>? = null,
    val input_message_content: InputMessageContent? = null,
    val reply_markup: InlineKeyboardMarkup? = null
) : InlineQueryResultItem