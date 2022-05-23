package telegrambot.messaging.commands.inline

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlinx.coroutines.withContext
import telegrambot.messaging.commands.models.Command
import telegrambot.messaging.context.local.LocalInlineContext

class InlineRequest(requestSchema: InlineRequestSchema) : Command(requestSchema) {
    override val localContext = LocalInlineContext(requestSchema)
    override val internalStaticContext = InlineRequestManager.internalStaticContext

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun emitEvent(event: Events) = GlobalScope.promise {
        when (event) {
            Events.BEFORE_ANSWER -> {
                val executable = executables[event]!!() as suspend (LocalInlineContext) -> Unit
                return@promise withContext(Dispatchers.Default) { executable(localContext) }
            }
            Events.ANSWER -> {
                val executable = executables[event]!!() as suspend InlineAnswer.(LocalInlineContext) -> Unit
                answer = InlineAnswer()
                return@promise withContext(Dispatchers.Default) { executable(answer, localContext) }
            }
            Events.AFTER_ANSWER -> {
                val executable = executables[event]!!() as suspend (LocalInlineContext) -> Unit
                return@promise withContext(Dispatchers.Default) { executable(localContext) }
            }
        }
    }

    lateinit var answer: InlineAnswer

    private val executables: MutableMap<Events, () -> dynamic> = mutableMapOf(
        Events.BEFORE_ANSWER to (requestSchema.functions["beforeAnswerFn"]
            ?: fun(): suspend (LocalInlineContext) -> Unit = {}),

        Events.ANSWER to (requestSchema.functions["onAnswerFn"]
            ?: throw IllegalStateException("no reply callback provided")),

        Events.AFTER_ANSWER to (requestSchema.functions["afterAnswerFn"]
            ?: fun(): suspend (LocalInlineContext) -> Unit = {})
    )

    enum class Events {
        BEFORE_ANSWER, ANSWER, AFTER_ANSWER
    }
}