package telegrambot.messaging.inlineresults.inputmessagecontents

import kotlinx.serialization.Serializable
import resources.ResourceStore
import telegrambot.messaging.MessageEntity

@Serializable
data class InputTextMessageContent(
    val message_text: String,
    val parse_mode: String = ResourceStore.telegramBotDefaults["parse_mode"] as String,
    val entities: Array<MessageEntity>? = null,
    val disable_web_page_preview: Boolean = false
) : InputMessageContent()