# Understanding the context
When you are writing bot logic, you will probably need to store some values to make them persist over command calls. So here's the logic that you can use freely in your project.

***

> **Note**: Any Context class is derived from MutableMap and has all its members and functions

## LocalContext
##### Basics.<br /><br />Introduced as `LocalChatContext` and `LocalInlineContext`.
LocalContext exists only in one instance of command call.\
This context is provided to `beforeReply`, `reply` and `afterReply` event listeners as the parameter.\
Also, there are some properties that exist after specific events. Here's a list of them:
> ***For LocalChatContext:***
> 1. `inputMessage` - available in `beforeReply`, `reply` and `afterReply`. Stands for user's message that bot is replying to.
> 2. `schemaFillers` - can be configured by user and empty by default. See [message scheming explanation](MessageScheming.md) for more info about this.
> 3. `sentMessage` - available only in `afterReply`. Represents a Message object if bot's reply was sent or null if wasn't.

> ***For LocalInlineContext:***
> 1. `query` - available in `beforeReply`, `reply` and `afterReply`. Represents the InlineQuery that was received by bot and is valid to be processed by any of your inline request listeners.
> 2. `matched` - `beforeReply`, `reply` and `afterReply`. Represents [RegExpMatch](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.js/-reg-exp-match/) instance with info about regex match result.
> 3. `schemaFillersArray` - can be configured by user and empty by default. See [message scheming explanation](MessageScheming.md) for more info about this.
> 4. `wasMessageSent` - available only in `afterReply`. `true` if message was sent and `false` if not.

***

## StaticContext
##### Context that persists over time but can bee accessed only in specific realms.<br /><br />Introduced as `PrivateStaticContext`, `InternalStaticContext` and `PublicStaticContext`.
### PrivateStaticContext
`PrivateStaticContext` exists as singleton for each command, so it can't be accessed outside but saves values over command calls. It is provided as argument for `onInit` so its members can be set there or at any time when the command is called.

### InternalStaticContext
`InternalStaticContext` exists as singleton separately for Chat and Inline realms, so you can't call member `foo` from inline answer builder **directly** while it was set in `registerChatCommands()` in [ChatCommandRegistration.kt](/src/main/kotlin/commandconfiguration/ChatCommandsRegistration.kt) file. Worth mentioning that you **can** access it through with `internalStaticContext` field of `ChatCommandManager` or `InlineRequestManager`. But do you really want to?

### PublicStaticContext *(or SharedStaticContext if it makes more sense)*
`PublicStaticContext` exists as singleton object and available absolutely anywhere.

# Example
```kotlin
/* ** [ChatCommandsRegistration.kt] ** */
    PublicStaticContext["public_context_msg"] = "This is PublicStaticContext field and it can be accessed everywhere"

    internalStaticContext["internal_context_msg"] = "This is InternalStaticContext field and can be accessed only in this realm (chat in this example)"
        
    register(
        "start"
    ) {
        onInit { privateStaticContext ->
            privateStaticContext["private_context_msg"] = "This is PrivateStaticContext field and can be accessed in beforeReply, reply & afterReply"
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