package telegrambot.messaging.context.annotations

@MustBeDocumented
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.CLASS)
internal annotation class Exists(vararg val existences: Existance)

internal enum class Existance {
    IN_LISTENER_INITIALIZER,
    BEFORE_REPLY_EVENT,
    IN_REPLY_EVENT,
    AFTER_REPLY_EVENT,
    BY_USER_INPUT,
    EVERYWHERE,
    IN_REALM
}