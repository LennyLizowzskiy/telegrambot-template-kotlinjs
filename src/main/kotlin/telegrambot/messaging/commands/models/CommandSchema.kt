package telegrambot.messaging.commands.models

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import telegrambot.messaging.context.static.PrivateStaticContext

abstract class CommandSchema {
    val functions: MutableMap<String, () -> dynamic> = mutableMapOf()
    val privateStaticContext = PrivateStaticContext()

    @OptIn(DelicateCoroutinesApi::class)
    fun onInit(logic: suspend (PrivateStaticContext) -> Unit)
        = GlobalScope.promise { logic(privateStaticContext) }
}