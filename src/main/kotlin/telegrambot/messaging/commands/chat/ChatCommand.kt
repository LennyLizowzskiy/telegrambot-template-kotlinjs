package telegrambot.messaging.commands.chat

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlinx.coroutines.withContext
import telegrambot.messaging.commands.models.Command
import telegrambot.messaging.context.local.LocalChatContext

class ChatCommand(completeSchema: ChatCommandSchema) : Command(completeSchema) {
    override val localContext = LocalChatContext(completeSchema)
    override val internalStaticContext = ChatCommandManager.internalStaticContext

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun emitEvent(event: Events) = GlobalScope.promise {
        when (event) {
            Events.BEFORE_REPLY -> {
                val executable = executables[event]!!() as suspend (LocalChatContext) -> Unit
                return@promise withContext(Dispatchers.Default) { executable(localContext) }
            }
            Events.REPLY -> {
                val executable = executables[event]!!() as suspend ChatCommandReply.(LocalChatContext) -> Unit
                reply = ChatCommandReply(localContext)
                return@promise withContext(Dispatchers.Default) { executable(reply, localContext) }
            }
            Events.AFTER_REPLY -> {
                val executable = executables[event]!!() as suspend (LocalChatContext) -> Unit
                return@promise withContext(Dispatchers.Default) { executable(localContext) }
            }
        }
    }

    lateinit var reply: ChatCommandReply

    private val executables: MutableMap<Events, () -> dynamic> = mutableMapOf(
        Events.BEFORE_REPLY to (completeSchema.functions["beforeReplyFn"]
            ?: fun(): suspend (LocalChatContext) -> Unit = {}),

        Events.REPLY to (completeSchema.functions["onReplyFn"]
            ?: throw IllegalStateException("no reply callback provided")),

        Events.AFTER_REPLY to (completeSchema.functions["afterReplyFn"]
            ?: fun(): suspend (LocalChatContext) -> Unit = {})
    )

    enum class Events {
        BEFORE_REPLY, REPLY, AFTER_REPLY
    }
}