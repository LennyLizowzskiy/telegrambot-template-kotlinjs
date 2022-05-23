package commandconfiguration

import telegrambot.messaging.commands.inline.InlineRequestManager
import telegrambot.messaging.inlineresults.InlineQueryResultArticle
import telegrambot.messaging.inlineresults.inputmessagecontents.InputTextMessageContent

fun registerInlineRequests() = with(InlineRequestManager) {
    register("default") {
        answer {
            addQueryResult(
                InlineQueryResultArticle(
                    title = "Title",
                    description = "Description",
                    input_message_content = InputTextMessageContent(
                        message_text = "Result message"
                    )
                )
            )
        }
    }
}