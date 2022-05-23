package telegrambot.messaging.commands.inline

import telegrambot.messaging.commands.models.CommandSchema
import telegrambot.messaging.context.local.LocalInlineContext


class InlineRequestSchema : CommandSchema() {
    fun beforeAnswer(logic: suspend (LocalInlineContext) -> Unit) {
        functions["beforeAnswerFn"] = fun() = logic
    }

    fun answer(logic: suspend InlineAnswer.(LocalInlineContext) -> Unit) {
        functions["onAnswerFn"] = fun() = logic
    }

    fun afterAnswer(logic: suspend (LocalInlineContext) -> Unit) {
        functions["afterAnswerFn"] = fun() = logic
    }
}