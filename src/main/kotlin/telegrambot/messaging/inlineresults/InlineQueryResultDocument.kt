package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import resources.ResourceStore
import telegrambot.messaging.inlineresults.inputmessagecontents.InputMessageContent
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultDocument(
    val type: String = "document",
    override var id: String? = null,
    val title: String,
    val caption: String? = null,
    val parse_mode: String = ResourceStore.telegramBotDefaults["parse_mode"] as String,
    val document_url: String,
    val mime_type: String,
    val description: String? = null,
    val caption_entities: Array<telegrambot.messaging.MessageEntity>? = null,
    val input_message_content: InputMessageContent? = null,
    val reply_markup: InlineKeyboardMarkup? = null,
    val thumb_url: String? = null,
    val thumb_width: Int? = null,
    val thumb_height: Int? = null
) : InlineQueryResultItem