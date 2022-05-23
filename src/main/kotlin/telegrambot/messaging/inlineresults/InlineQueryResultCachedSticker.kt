package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import telegrambot.messaging.inlineresults.inputmessagecontents.InputMessageContent
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultCachedSticker(
    val type: String = "sticker",
    override var id: String? = null,
    val sticker_file_id: String,
    val reply_markup: InlineKeyboardMarkup? = null,
    val input_message_content: InputMessageContent? = null
) : InlineQueryResultItem