# Working with JavaScript dependencies inside Kotlin/JS
You will probably need to use some NPM modules in your project (but hey, Maven also supports Kotlin/JS modules, so you can search for something useful there too).
***
## Adding NPM dependencies
> **Note**: Node version for this project is *v16.15.0* (can be changed in `nodeVersion` of [build.gradle.kts](/build.gradle.kts)).

Refer to [this page](https://kotlinlang.org/docs/using-packages-from-npm.html) to add dependencies for your project.
You can check [dependencies/TelegramBot.kt](/src/main/kotlin/javascript/dependencies/TelegramBot.kt) for more detailed example.\
Also, the more traditional way of importing modules is supported - just use function `require(moduleNameOrPath: String)` as you could do in JavaScript.