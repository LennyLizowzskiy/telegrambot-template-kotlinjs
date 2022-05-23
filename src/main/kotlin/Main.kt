//import telegrambot.commands.registerChatCommands
import commandconfiguration.registerChatCommands
import commandconfiguration.registerInlineRequests
import commandconfiguration.registerMessageSchemas
import javascript.dependencies.applyJsDependenciesSettings
import telegrambot.listenChatCommands
import telegrambot.listenInlineQueries
import telegrambot.setupTelegramBot

fun main() {
    applyJsDependenciesSettings()

    registerMessageSchemas()

    registerChatCommands()
    registerInlineRequests()

    setupTelegramBot()
    listenChatCommands()
    listenInlineQueries()

    console.log("Listening...")
}