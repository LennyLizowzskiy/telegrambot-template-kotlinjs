# Template for creating Telegram bots based on Kotlin/NodeJS

This project was designed to make creation of Telegram bots based on NodeJS much easier while containing all power of Kotlin.
***

### Documentation:
1. [Working with NPM in Kotlin/JS](/docs/WorkingWithJavascript.md)
2. [Adding message schemas](/docs/MessageScheming.md)
3. [Adding command listeners](/docs/CommandRegistration.md)
4. [Understanding the context](/docs/UnderstandingTheContext.md)

### How to use:
1. Install NodeJS v16.15.0
2. Fork or clone this repository
3. Follow the documentation guides to create the bot that you need
4. Run `./gradlew build` in terminal
5. After build was successfully finished, run `node ./build/js/packages/KotlinJsTelegrambotTemplate` (or some other name in case you changed `settings.gradle.kts`)