package telegrambot.messaging.commands.chat

import telegrambot.messaging.commands.models.CommandSchema
import telegrambot.messaging.context.local.LocalChatContext

class ChatCommandSchema : CommandSchema() {
    fun beforeReply(logic: suspend (LocalChatContext) -> Unit) {
        functions["beforeReplyFn"] = fun() = logic
    }

    fun reply(logic: suspend ChatCommandReply.(LocalChatContext) -> Unit) {
        functions["onReplyFn"] = fun() = logic
    }

    fun afterReply(logic: suspend (LocalChatContext) -> Unit) {
        functions["afterReplyFn"] = fun() = logic
    }
}