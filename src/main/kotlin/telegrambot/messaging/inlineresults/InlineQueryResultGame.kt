package telegrambot.messaging.inlineresults

import kotlinx.serialization.Serializable
import telegrambot.messaging.replymarkups.InlineKeyboardMarkup

@Serializable
data class InlineQueryResultGame(
    val type: String = "game",
    override var id: String? = null,
    val game_short_name: String,
    val reply_markup: InlineKeyboardMarkup? = null
) : InlineQueryResultItem