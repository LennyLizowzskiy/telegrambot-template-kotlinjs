# Message scheming
Message schemas are introduced to reduce boilerplate text while you have commands in both chat and inline realm with similar output.

***

## Creating schema
To create schema you will need to edit [MessageSchemaRegistration.kt](/src/main/kotlin/commandconfiguration/MessageSchemaRegistration.kt). In curly braces of In `registerMessageSchemas()` add your own schemas.\
Follow this simple syntax there: `register(schemaName: String, schemaText: String)`\
In case you want to add patterns to replace them with actual values in your command listener code later, use %-patterns in `schemaText` (example: `"Normal text and %replaceMe pattern"`; [detailed example below](MessageScheming.md#Detailed-example)).

## Usage
### Chat commands
1. Provide `localContext.schemaFillers: Array<Pair<Pattern, Any>>` (where `Pattern` stands for pattern you used while creating your schema but without `%` sign) in `beforeReply` listener.
2. Use `applyMessageSchema(MessageSchema)` in `reply` listener.

### Inline requests answers
1. Provide `localContext.schemaFillers: Array<Array<Pair<Pattern, Any>>>` (where `Pattern` stands for pattern you used while creating your schema but without `%` sign) in `beforeAnswer` listener. The main difference here is that you can store multiple schema fillers by design.
2. Use `MessageSchema.storage[schemaName: String].applyParameters(*localContext.schemaFillers[index: Int])` where you want to (titles/descriptions/etc.).

### Detailed example
```kotlin
/* ** [MessageSchemaRegistration.kt] ** */
    register("celldweller",
        """
        I've been here many times before
        Standing here with broken %thing knocking on this broken %anotherThing
        I can't decide which way to go
        And this is just another time I prove to you
        How little I must know
        Here I am back at the start
        Standing here with empty %sth flowing from an empty heart
        I'm letting down and letting go
        And just like every other time I prove to you
        How little I must know
        """.trimIndent() // trimIndent() removes all unnecessary whitespace 
    )
/* ***** */
```
```kotlin
/* ** [ChatCommandRegistration.kt] ** */
    register("start") {
        beforeReply { localContext ->
            localContext.schemaFillers = arrayOf(
                "thing" to getThingFromNet().await(), // "house"
                "anotherThing" to "soup",
                "sth" to getAnotherNonPersistentValue() // "glass"
            )
        }
        
        reply {
            applyMessageSchema(MessageSchema.storage["celldweller"]!!) 
            // ^ this equals to this ->
            // text += MessageSchema.storage["celldweller"]!!.applyParameters(*localContext.schemaFillers)
        }
    }
/* ***** */


/* ** [InlineRequestsRegistration.kt] ** */
    register("default") {
        beforeAnswer { localContext ->
            localContext.schemaFillers = arrayOf(
                "thing" to getThingFromNet().await(), // "house"
                "anotherThing" to "soup",
                "sth" to getAnotherNonPersistentValue() // "glass"
            )
        }
        
        reply {
            addQueryResult(
                InlineQueryResultArticle(
                    title = "Example answer",
                    input_message_content = MessageSchema.storage["celldweller"]!!.applyParameters(*localContext.schemaFillers[0])
                )
            )
        }
    }
/* ***** */


// Expected output by both commands: 
// 
// I've been here many times before 
// Standing here with broken house knocking on this broken soup
// I can't decide which way to go
// And this is just another time I prove to you
// How little I must know
// Here I am back at the start
// Standing here with empty glass flowing from an empty heart
// I'm letting down and letting go
// And just like every other time I prove to you
// How little I must know
```