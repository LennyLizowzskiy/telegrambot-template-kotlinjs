package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import telegrambot.messaging.inlineresults.inputmessagecontents.InputMessageContent
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultCachedPhoto(
    val type: String = "photo",
    override var id: String? = null,
    val photo_file_id: String,
    val title: String? = null,
    val description: String? = null,
    val caption: String? = null,
    val parse_mode: String? = null,
    val caption_entities: Array<telegrambot.messaging.MessageEntity>? = null,
    val reply_markup: InlineKeyboardMarkup? = null,
    val input_message_content: InputMessageContent? = null
) : InlineQueryResultItem