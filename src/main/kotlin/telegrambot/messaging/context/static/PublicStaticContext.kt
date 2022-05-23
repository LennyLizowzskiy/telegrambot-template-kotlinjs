package telegrambot.messaging.context.static

import telegrambot.messaging.context.annotations.Existance
import telegrambot.messaging.context.annotations.Exists

/**
 * Available both inline & chat realms and all commands of them
 */
@Exists(Existance.EVERYWHERE)
object PublicStaticContext : StaticContext() {
}