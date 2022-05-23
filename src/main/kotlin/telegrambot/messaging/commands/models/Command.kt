package telegrambot.messaging.commands.models

import telegrambot.messaging.context.local.LocalContext
import telegrambot.messaging.context.static.InternalStaticContext
import telegrambot.messaging.context.static.PrivateStaticContext
import telegrambot.messaging.context.static.PublicStaticContext

abstract class Command(commandSchema: CommandSchema) {
    abstract val localContext: LocalContext
    abstract val internalStaticContext: InternalStaticContext
    val privateStaticContext = PrivateStaticContext()

    val publicStaticContext = PublicStaticContext
}