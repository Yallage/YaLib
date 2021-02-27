package com.rabbitown.yalib.module.chat.text

import com.google.gson.JsonArray
import com.rabbitown.yalib.module.chat.text.event.hover.Content
import com.rabbitown.yalib.module.chat.text.impl.PlainTextElement
import com.rabbitown.yalib.module.nms.base.chat.ChatBaseComponent
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.chat.ComponentSerializer

/**
 * Represents a [Raw JSON Text](https://minecraft.gamepedia.com/Raw_JSON_text_format).
 *
 * @author Milkory
 */
class JSONText() : ArrayList<IJSONTextElement>(), Content, IJSONTextElement {

    constructor(vararg elements: IJSONTextElement) : this() {
        addAll(elements)
    }

    override fun toJsonTree(): JsonArray {
        val array = JsonArray()
        forEach { array.add(it.toJsonTree()) }
        return array
    }

    override fun toString(): String {
        if (size == 1) {
            val value = get(0)
            if (value is PlainTextElement) return value.text
        }
        return toJsonTree().toString()
    }

    override fun toNMS() = ChatBaseComponent.newInstance(this)

    /** Convert to an array of [BaseComponent]. */
    fun toMd5BaseComponent(): Array<BaseComponent> = ComponentSerializer.parse(toString())

    companion object {

        @JvmStatic fun serialize(origin: String): JSONText = TODO()

        fun Iterable<IJSONTextElement>.toJSONText() = JSONText().apply {
            addAll(this@toJSONText)
        }

    }

}