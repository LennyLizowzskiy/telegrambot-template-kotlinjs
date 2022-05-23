package telegrambot.messaging.context.local

import telegrambot.messaging.commands.models.CommandSchema
import telegrambot.messaging.context.Context
import telegrambot.messaging.context.annotations.Existance
import telegrambot.messaging.context.annotations.Exists

/**
 * Available only in one current instance
 */
@Exists(Existance.BEFORE_REPLY_EVENT, Existance.IN_REPLY_EVENT, Existance.AFTER_REPLY_EVENT)
abstract class LocalContext(commandSchema: CommandSchema) : Context() {
    val privateStaticContext = commandSchema.privateStaticContext
}