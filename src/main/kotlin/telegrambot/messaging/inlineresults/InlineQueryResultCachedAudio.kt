package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import resources.ResourceStore
import telegrambot.messaging.inlineresults.inputmessagecontents.InputMessageContent
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultCachedAudio(
    val type: String = "audio",
    override var id: String? = null,
    val audio_file_id: String,
    val caption: String,
    val parse_mode: String = ResourceStore.telegramBotDefaults["parse_mode"] as String,
    val caption_entities: Array<telegrambot.messaging.MessageEntity>? = null,
    val reply_markup: InlineKeyboardMarkup? = null,
    val input_message_content: InputMessageContent? = null
) : InlineQueryResultItem