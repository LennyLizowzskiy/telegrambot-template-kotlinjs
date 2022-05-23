package telegrambot.messaging.context.local

import telegrambot.messaging.Message
import telegrambot.messaging.commands.chat.ChatCommandSchema
import telegrambot.messaging.context.annotations.Existance
import telegrambot.messaging.context.annotations.Exists
import telegrambot.messaging.response.chat.SimpleMessage

class LocalChatContext(chatCommandSchema: ChatCommandSchema) : LocalContext(chatCommandSchema) {
    @Exists(Existance.BEFORE_REPLY_EVENT, Existance.IN_REPLY_EVENT, Existance.AFTER_REPLY_EVENT)
    var inputMessage: SimpleMessage?
        get() = get("message")?.unsafeCast<SimpleMessage>()
        set(value) = put("message", value)

    @Exists(Existance.BY_USER_INPUT)
    var schemaFillers: Array<Pair<String, dynamic>>
        get() = get("schemaFillers")?.unsafeCast<Array<Pair<String, dynamic>>>() ?: emptyArray()
        set(value) = put("schemaFillers", value)

    @Exists(Existance.AFTER_REPLY_EVENT)
    var sentMessage: Message?
        get() = get("sentMessage")?.unsafeCast<Message>()
        set(value) = put("sentMessage", value)
}