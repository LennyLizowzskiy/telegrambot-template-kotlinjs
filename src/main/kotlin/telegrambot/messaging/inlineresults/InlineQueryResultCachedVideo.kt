package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import resources.ResourceStore
import telegrambot.messaging.inlineresults.inputmessagecontents.InputMessageContent
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultCachedVideo(
    val type: String = "video",
    override var id: String? = null,
    val video_file_id: String,
    val title: String,
    val description: String? = null,
    val caption: String? = null,
    val parse_mode: String = ResourceStore.telegramBotDefaults["parse_mode"] as String,
    val caption_entities: Array<telegrambot.messaging.MessageEntity>? = null,
    val reply_markup: InlineKeyboardMarkup? = null,
    val input_message_content: InputMessageContent? = null
) : InlineQueryResultItem