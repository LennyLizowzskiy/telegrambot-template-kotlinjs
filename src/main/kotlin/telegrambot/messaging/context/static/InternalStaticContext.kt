package telegrambot.messaging.context.static

import telegrambot.messaging.context.annotations.Existance
import telegrambot.messaging.context.annotations.Exists

/**
 * Available only in chat context realm & all instances of all commands
 */
@Exists(Existance.IN_REALM)
class InternalStaticContext : StaticContext() {
}