# Adding command listeners

***

### Table of contents:
1. [Command registration](CommandRegistration.md#Command-registration)
2. [Event listeners](CommandRegistration.md#Event-listeners)
3. [Respond configuration](CommandRegistration.md#Respond-configuration)
4. [Examples](CommandRegistration.md#Examples)

## Command registration
***
Chat commands can be registered in [ChatCommandsRegistration.kt](/src/main/kotlin/commandconfiguration/ChatCommandsRegistration.kt)\
*Syntax:* `register("start") { ... }`

Inline request answers can be registered in [InlineRequestsRegistration.kt](/src/main/kotlin/commandconfiguration/InlineRequestsRegistration.kt)\
*Syntax:* `register("default") { ... }` *or* `register("answerOnlyToMessageWithNumbers", RegExp("\d+"))` 

## Event listeners
***
> **Note:** All functions presented as event listeners are suspending (asynchronous)
### Chat command listeners
1. `onInit` (**Optional**) - executed right on the registration.<br />Supplied with [PrivateStaticContext](UnderstandingTheContext.md#PrivateStaticContext) argument as `it`.
2. `beforeReply` (**Optional**) - executed before reply was done.<br />Supplied with [LocalChatContext](UnderstandingTheContext.md#LocalContext) argument as `it`.
3. `reply` (**Required**) - executed on reply message configuration.<br />Supplied with *ChatCommandReplyBuilder* as `this` and[LocalChatContext](UnderstandingTheContext.md#LocalContext) as `it`.
4. `afterReply` (**Optional**) - executed after reply was done and callback was received.<br />Supplied with [LocalChatContext](UnderstandingTheContext.md#LocalContext) argument as `it`. `LocalChatContext.sentMessage` field become available here if message was successfully sent.

### Inline request listeners
1. `onInit` (**Optional**) - executed right on the registration.<br />Supplied with [PrivateStaticContext](UnderstandingTheContext.md#PrivateStaticContext) argument as `it`.
2. `beforeReply` (**Optional**) - executed before answer was done.<br />Supplied with [LocalChatContext](UnderstandingTheContext.md#LocalContext) argument as `it`.
3. `reply` (**Required**) - executed on answer results configuration.<br />Supplied with *InlineAnswerBuilder* as `this` and[LocalInlineContext](UnderstandingTheContext.md#LocalContext) as `it`.
4. `afterReply` (**Optional**) - executed after reply was done and callback was received.<br />Supplied with [LocalInlineContext](UnderstandingTheContext.md#LocalContext) argument as `it`. `LocalInlineContext.wasMessageSent` field become available here if the request was satisfied.

## Respond configuration
***
### Chat command reply configuration
Reply message text configuration is performed in `text` field of `reply { ... }` event listener. *Also, you can use [message schemas](MessageScheming.md) to reduce boilerplate text.*

<br />

### Inline answer configuration
Inline answers are being configured in `answer { ... }` event listener. You can add inline answers with `addQueryResult()` function. Each [inline query result](https://core.telegram.org/bots/api#inlinequeryresult) from *Telegram Bot API* is available as class constructor. Example below.

## Examples
***

### Chat command registration example:
> **Note:** See [context explanation](UnderstandingTheContext.md) to understand what Static and Local contexts are.
```kotlin
/* ** [ChatCommandsRegistration.kt] ** */
    register(
        "start" // chat command name that will be used by bot
    ) {
        onInit { privateStaticContext ->
            privateStaticContext["private_context_msg"] = "This is PrivateStaticContext field"
            
            println("This message will be printed on command registration")
        }
        
        beforeReply { localContext ->
            println("This message will be printed to terminal before reply")
            
            localContext["local_context_msg"] = "This is LocalContext field"
        }
        
        reply { localContext ->
            println("This message will be printed to terminal on reply event")
            
            text = "This is text of the reply message"
        }
        
        afterReply { localContext ->
            println("This message will be printed to terminal before reply")
        }
    }
/* ***** */
```

<br />
<br />

### Inline request answer registration example:
> **Note:** See [context explanation](UnderstandingTheContext.md) to understand what Static and Local contexts are.
```kotlin
/* ** [InlineCommandsRegistration.kt] ** */
    register(
        "start", // identifier
        RegExp("\d") // this regex should be matched to perform all logic below
    ) {
        onInit { privateStaticContext ->
            privateStaticContext["private_context_msg"] = "This is PrivateStaticContext field"
            
            println("This message will be printed on inline request listener registration")
        }
        
        beforeAnswer { localContext ->
            println("This message will be printed to terminal before answer")
            
            localContext["local_context_msg"] = "This is LocalContext field"
        }
        
        answer { localContext ->
            println("This message will be printed to terminal on answer event")
            
            addQueryResult(
                InlineQueryResultArticle( // every type of InlineQueryResult presented in Telegram Bot API docs is available as class and can be used in [addQueryResult]
                    title = "Title",
                    description = "Description",
                    input_message_content = InputTextMessageContent(
                        message_text = "Result message"
                    )
                )
            )
        }
        
        afterAnswer { localContext ->
            println("This message will be printed to terminal before answer")
            
            println("Was message sent? " + localContext.wasMessageSent)
        }
    }
/* ***** */
```