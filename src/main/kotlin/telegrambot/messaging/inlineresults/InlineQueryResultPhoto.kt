package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import resources.ResourceStore
import telegrambot.messaging.inlineresults.inputmessagecontents.InputMessageContent
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultPhoto(
    val type: String = "photo",
    override var id: String? = null,
    val photo_url: String,
    val thumb_url: String,
    val photo_width: Int? = null,
    val photo_height: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val caption: String? = null,
    val parse_mode: String = ResourceStore.telegramBotDefaults["parse_mode"] as String,
    val caption_entities: Array<telegrambot.messaging.MessageEntity>? = null,
    val input_message_content: InputMessageContent? = null,
    val reply_markup: InlineKeyboardMarkup? = null
) : InlineQueryResultItem