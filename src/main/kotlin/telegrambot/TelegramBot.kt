package telegrambot

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.promise
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToDynamic
import resources.ResourceStore
import telegrambot.messaging.commands.chat.ChatCommand
import telegrambot.messaging.commands.chat.ChatCommandManager
import telegrambot.messaging.commands.inline.InlineRequest
import telegrambot.messaging.commands.inline.InlineRequestManager
import telegrambot.messaging.response.inline.InlineQuery
import kotlin.js.RegExp
import kotlin.js.RegExpMatch
import kotlin.js.json
import javascript.dependencies.TelegramBot as TelegramBotClass

lateinit var TelegramBot: TelegramBotClass
lateinit var jsoner: Json

@OptIn(ExperimentalSerializationApi::class)
fun setupTelegramBot() {
    TelegramBot = TelegramBotClass(
        ResourceStore.sensitiveInformation["authorization"].asDynamic()["telegram_bot_api_key"]!! as String,
        json(
            "polling" to true
        )
    )

    jsoner = Json {
        classDiscriminator = "_type"
        encodeDefaults = true
        explicitNulls = false
    }
}

@OptIn(DelicateCoroutinesApi::class, ExperimentalSerializationApi::class)
fun listenChatCommands() {
    TelegramBot.onText(RegExp("(?<=\\/)\\w+")) { message, matched ->
        GlobalScope.promise {
            val command = ChatCommandManager.storage[matched[0]?.lowercase()]?.invoke() ?: return@promise
            command.localContext.inputMessage = message

            command.emitEvent(ChatCommand.Events.BEFORE_REPLY).await()
            command.emitEvent(ChatCommand.Events.REPLY).await()

            if (command.reply.options.is_telegram_reply == true) {
                command.reply.options.is_telegram_reply = null // so it won't be sent to Telegram Bot API
                command.reply.options.reply_to_message_id = message.message_id
            }
            val options = jsoner.encodeToDynamic(command.reply.options)

            command.localContext.sentMessage = TelegramBot.sendMessage(message.chat.id, command.reply.text, options).await()

            command.emitEvent(ChatCommand.Events.AFTER_REPLY)
        }
    }
}

@OptIn(DelicateCoroutinesApi::class, ExperimentalSerializationApi::class)
fun listenInlineQueries() {
    TelegramBot.on("inline_query") { query: InlineQuery, _ ->
        GlobalScope.promise {
            var _request: InlineRequest? = null
            var matched: RegExpMatch? = null

            InlineRequestManager.storage.forEach { entry ->
                val (regex, requestInstancer) = entry.value

                if (regex.test(query.query)) {
                    matched = regex.exec(query.query)
                    _request = requestInstancer()
                }
            }

            val inlineRequest =
                _request ?: InlineRequestManager.storage["default"]?.let { it.second() } ?: return@promise

            if (inlineRequest.answer.results.isEmpty()) return@promise

            inlineRequest.localContext.matched = matched
            inlineRequest.localContext.query = query

            inlineRequest.emitEvent(InlineRequest.Events.BEFORE_ANSWER).await()
            inlineRequest.emitEvent(InlineRequest.Events.ANSWER).await()

            val resultArray: ArrayList<dynamic> = arrayListOf()
            inlineRequest.answer.results.forEach {
                resultArray.add(jsoner.encodeToDynamic(it))
            }

            inlineRequest.localContext.wasMessageSent = TelegramBot.answerInlineQuery(query.id, resultArray.toTypedArray()).await()

            inlineRequest.emitEvent(InlineRequest.Events.AFTER_ANSWER).await()
        }
    }
}