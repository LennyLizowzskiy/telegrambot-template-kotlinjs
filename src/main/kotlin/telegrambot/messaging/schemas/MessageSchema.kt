package telegrambot.messaging.schemas

typealias Pattern = String

/**
 * Message schema for bot to send
 *
 * Can be used with patterns like %X where X - identifier
 *
 * Note: HTML markup is used for Markdown formatting in result message
 */
value class MessageSchema(private val value: String) {
    /**
     * Applies given parameters to the schema
     *
     * @param[parameters] Vararg of [Pair] where [Pair.first] - pattern identifier and [Pair.second] - value that should be inserted instead of the placeholder
     * @return Complete string
     */
    fun applyParameters(vararg parameters: Pair<Pattern, dynamic>): String {
        var local = value
        parameters.forEach{
            local = local.replace("%" + it.first, it.second.toString(), true)
        }
        return local
    }

    override fun toString() = value

    companion object {
        val storage: MutableMap<String, MessageSchema> = mutableMapOf()

        /**
         * Registers [MessageSchema]
         *
         * @see MessageSchema
         * @return Pair of identifier and created schema
         */
        fun register(name: String, value: String): Pair<String, MessageSchema> {
            storage[name] = MessageSchema(value)

            return name to storage[name]!!
        }
    }
}