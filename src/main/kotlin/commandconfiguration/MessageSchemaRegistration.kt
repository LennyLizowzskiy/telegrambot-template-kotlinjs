package commandconfiguration

import telegrambot.messaging.schemas.MessageSchema

fun registerMessageSchemas(): Unit = with(MessageSchema) {
    register("example",
        """
        I've been here many times before
        Standing here with broken hands knocking on this broken %thing
        I can't decide which way to go
        And this is just another time %subject prove to you
        How little %subject must know
        Here %subject am back at the %something
        Standing here with empty words flowing from an empty %object
        I'm letting down and letting go
        And just like every other time I prove to you
        How little %subject must know
        """.trimIndent()
    )
}