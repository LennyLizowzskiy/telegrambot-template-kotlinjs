package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import resources.ResourceStore
import telegrambot.messaging.inlineresults.inputmessagecontents.InputMessageContent
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultVideo(
    val type: String = "video",
    override var id: String? = null,
    val video_url: String,
    val mime_type: String,
    val thumb_url: String,
    val title: String,
    val caption: String? = null,
    val parse_mode: String = ResourceStore.telegramBotDefaults["parse_mode"] as String,
    val video_width: Int? = null,
    val video_height: Int? = null,
    val video_duration: Int? = null,
    val description: String? = null,
    val caption_entities: Array<telegrambot.messaging.MessageEntity>? = null,
    val input_message_content: InputMessageContent? = null,
    val reply_markup: InlineKeyboardMarkup? = null
) : InlineQueryResultItem