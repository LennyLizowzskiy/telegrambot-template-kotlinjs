package telegrambot.messaging.context.local

import telegrambot.messaging.commands.inline.InlineRequestSchema
import telegrambot.messaging.context.annotations.Existance
import telegrambot.messaging.context.annotations.Exists
import telegrambot.messaging.response.inline.InlineQuery
import kotlin.js.RegExpMatch

class LocalInlineContext(requestSchema: InlineRequestSchema) : LocalContext(requestSchema) {
    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    @Exists(Existance.BEFORE_REPLY_EVENT, Existance.IN_REPLY_EVENT, Existance.AFTER_REPLY_EVENT)
    var query: InlineQuery?
        get() = get("query") as? InlineQuery
        set(value) = put("query", value).unsafeCast<Unit>()

    @Exists(Existance.BEFORE_REPLY_EVENT, Existance.IN_REPLY_EVENT, Existance.AFTER_REPLY_EVENT)
    var matched: RegExpMatch?
        get() = get("regExpMatch")?.unsafeCast<RegExpMatch>()
        set(value) = put("regExpMatch", value)

    @Exists(Existance.BY_USER_INPUT)
    var schemaFillersArrays: Array<Array<Pair<String, dynamic>>>
        get() = get("schemaFillers")?.unsafeCast<Array<Array<Pair<String, dynamic>>>>() ?: emptyArray()
        set(value) = put("schemaFillers", value)

    @Exists(Existance.AFTER_REPLY_EVENT)
    var wasMessageSent: Boolean
        get() = get("wasMessageSent") == true
        set(value) = put("wasMessageSent", value)
}