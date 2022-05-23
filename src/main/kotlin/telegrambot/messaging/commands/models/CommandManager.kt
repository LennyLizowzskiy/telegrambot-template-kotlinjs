package telegrambot.messaging.commands.models

import telegrambot.messaging.context.static.InternalStaticContext

abstract class CommandManager {
    abstract val storage: MutableMap<String, *>

    val internalStaticContext = InternalStaticContext()
}