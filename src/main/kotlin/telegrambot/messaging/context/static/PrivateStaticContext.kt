package telegrambot.messaging.context.static

import telegrambot.messaging.context.annotations.Existance
import telegrambot.messaging.context.annotations.Exists

/**
 * Available only for one command and all instances of it
 */
@Exists(Existance.IN_LISTENER_INITIALIZER, Existance.BEFORE_REPLY_EVENT, Existance.IN_REPLY_EVENT, Existance.AFTER_REPLY_EVENT)
class PrivateStaticContext : StaticContext() {
}