package commandconfiguration

import telegrambot.messaging.commands.chat.ChatCommandManager
import telegrambot.messaging.context.static.PublicStaticContext

fun registerChatCommands() = with(ChatCommandManager) {
    PublicStaticContext["pubstest"] = "This is public static context"

    internalStaticContext["intstest"] = "This is internal static context"

    register("start") {
        onInit { privateContext ->
            privateContext["pstest"] = "This is private static context"
        }

        beforeReply { localContext ->
            localContext["lctest"] = "This is local context"
        }

        reply {
            text = "This is reply text"
        }

        afterReply { localContext ->

        }
    }
}