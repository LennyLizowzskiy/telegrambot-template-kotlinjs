package javascript

// JSO - JSON-like Javascript [object Object]
object JSOAdditions {
    /**
     * Convert JSO to [List]
     *
     * @return Entries of JSO as [List] of [Pair] of [String] & [Any]?
     * @see [Source](https://gist.github.com/Foso/f254410ba99722101b000e03181c4f45)
     */
    fun entriesOf(jsObject: dynamic): List<Pair<String, Any?>> =
        (js("Object.entries") as (dynamic) -> Array<Array<Any?>>)
            .invoke(jsObject)
            .map { entry -> entry[0] as String to entry[1] }

    /**
     * Represent JSO as [Map]
     *
     * @see [Source](https://gist.github.com/Foso/f254410ba99722101b000e03181c4f45)
     */
    fun entriesOfAsMap(jsObject: dynamic): Map<String, Any?> =
        entriesOf(jsObject).toMap()
}