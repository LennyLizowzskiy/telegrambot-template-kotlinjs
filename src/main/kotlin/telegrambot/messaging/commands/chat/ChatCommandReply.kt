package telegrambot.messaging.commands.chat

import kotlinx.serialization.Serializable
import resources.ResourceStore
import telegrambot.messaging.MessageEntity
import telegrambot.messaging.commands.models.CommandReply
import telegrambot.messaging.context.local.LocalChatContext
import telegrambot.messaging.replymarkups.ReplyMarkup
import telegrambot.messaging.schemas.MessageSchema

class ChatCommandReply(val context: LocalChatContext) : CommandReply() {
    var text = ""

    fun applyMessageSchema(schema: MessageSchema) {
        text += schema.applyParameters(*context.schemaFillers)
    }


    var options = Options()

    fun setOptions(optionsUpdater: Options.() -> Unit) = options.apply(optionsUpdater)

    @Serializable
    data class Options(
        var parse_mode: String = ResourceStore.telegramBotDefaults["parse_mode"]!!.toString(),
        var entities: Array<MessageEntity>? = null,
        var disable_web_page_preview: Boolean = true,
        var disable_notification: Boolean = true,
        var protect_content: Boolean = false,
        var is_telegram_reply: Boolean? = true,
        var reply_to_message_id: Int? = null,
        var allow_sending_without_reply: Boolean = true,
        var reply_markup: ReplyMarkup? = null
    )
}