package telegrambot.messaging.commands.inline

import telegrambot.messaging.commands.models.CommandReply
import telegrambot.messaging.inlineresults.InlineQueryResultItem

class InlineAnswer() : CommandReply() {
    var results: Array<InlineQueryResultItem> = emptyArray()

    fun addQueryResult(item: InlineQueryResultItem) {
        results += item.apply {
            if (this.id == null && this@InlineAnswer.options.autoAssignIds)
                this.id = this@InlineAnswer.hashCode().toString() + "_" + results.size.toString()
        }
    }

    var options = Options()

    fun setOptions(block: Options.() -> Unit) {
        options = Options().apply(block)
    }

    data class Options(
        val autoAssignIds: Boolean = true,
        val cacheTime: Int? = null,
        val isPersonal: Boolean? = null,
        val nextOffset: String? = null,
        val switchPmText: String? = null,
        val switchPmParameter: String? = null
    )
}