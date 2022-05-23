package telegrambot.messaging.commands.inline

import telegrambot.messaging.commands.models.CommandManager
import kotlin.js.RegExp

object InlineRequestManager : CommandManager() {
    override val storage: MutableMap<String, Pair<RegExp, () -> InlineRequest>> = mutableMapOf()

    private val noMatchRegExp = RegExp("(?:)")

    fun register(name: String, regex: RegExp = noMatchRegExp, logic: InlineRequestSchema.() -> Unit) {
        storage[name.lowercase()] = regex to fn@{
            return@fn InlineRequest(InlineRequestSchema().apply(logic))
        }
    }
}