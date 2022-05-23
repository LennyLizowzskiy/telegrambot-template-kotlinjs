package javascript.dependencies

import telegrambot.messaging.response.chat.SimpleMessage
import kotlin.js.Json
import kotlin.js.Promise
import kotlin.js.RegExp
import kotlin.js.RegExpMatch

/**
 * Wrapper for [Telegram Bot API](https://core.telegram.org/bots/api).
 *
 * [Open at NPMJS](https://www.npmjs.com/package/node-telegram-bot-api)
 */
@JsModule("node-telegram-bot-api")
@JsNonModule
external class TelegramBot(botApiKey: String, options: Json?) {
    /**
     * Activates callback on specified event
     *
     * [Available 'eventName'](https://github.com/yagop/node-telegram-bot-api/blob/master/doc/usage.md#events)
     */
    fun on(eventName: String, callback: (dynamic, dynamic) -> Unit)

    /**
     * Activates callback when receiving message that satisfies specified regular expression
     */
    fun onText(regexp: RegExp, callback: (SimpleMessage, RegExpMatch) -> Unit)

    /**
     * Send text message
     *
     * ['options' fields](https://core.telegram.org/bots/api#sendmessage)
     */
    fun sendMessage(chat_id: dynamic, text: String, options: Json?): Promise<telegrambot.messaging.Message>

    /**
     * Edit message text
     *
     * ['options' fields](https://core.telegram.org/bots/api#editmessagetext)
     */
    fun editMessageText(text: String): Promise<dynamic>
    fun editMessageText(text: String, options: Json): Promise<dynamic>

    /**
     * Edit message caption
     */
    fun editMessageCaption(caption: String): Promise<dynamic>
    fun editMessageCaption(caption: String, options: Json): Promise<dynamic>

    /**
     *
     *
     * ['options' fields](https://core.telegram.org/bots/api#answerinlinequery)
     */
    fun answerInlineQuery(inline_query_id: dynamic, results: Array<dynamic>): Promise<Boolean>
    fun answerInlineQuery(inline_query_id: dynamic, results: Array<dynamic>, options: Json)
}