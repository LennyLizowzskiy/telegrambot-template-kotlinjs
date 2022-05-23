package telegrambot.messaging.commands.chat

import telegrambot.messaging.commands.models.CommandManager

object ChatCommandManager : CommandManager() {
    override val storage: MutableMap<String, () -> ChatCommand> = mutableMapOf()

    fun register(name: String, logic: ChatCommandSchema.() -> Unit) {
        storage[name.lowercase()] = fn@{
            return@fn ChatCommand(ChatCommandSchema().apply(logic))
        }
    }
}