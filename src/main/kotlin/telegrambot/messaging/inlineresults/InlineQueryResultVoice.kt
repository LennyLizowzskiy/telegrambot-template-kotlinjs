package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import resources.ResourceStore
import telegrambot.messaging.inlineresults.inputmessagecontents.InputMessageContent
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultVoice(
    val type: String = "voice",
    override var id: String? = null,
    val voice_url: String,
    val title: String,
    val caption: String? = null,
    val parse_mode: String = ResourceStore.telegramBotDefaults["parse_mode"] as String,
    val voice_duration: Int? = null,
    val caption_entities: Array<telegrambot.messaging.MessageEntity>? = null,
    val input_message_content: InputMessageContent? = null,
    val reply_markup: InlineKeyboardMarkup? = null
) : InlineQueryResultItem