package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import telegrambot.messaging.inlineresults.inputmessagecontents.InputMessageContent
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultLocation(
    val type: String = "location",
    override var id: String? = null,
    val latitude: Float,
    val longitude: Float,
    val title: String,
    val horizontal_accuracy: Float? = null,
    val live_period: Int? = null,
    val heading: Int? = null,
    val proximity_alert_radius: Int? = null,
    val reply_markup: InlineKeyboardMarkup? = null,
    val input_message_content: InputMessageContent? = null,
    val thumb_url: String? = null,
    val thumb_width: Int? = null,
    val thumb_height: Int? = null
) : InlineQueryResultItem