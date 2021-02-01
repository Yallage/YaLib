package com.rabbitown.yalib.module.chat.text

import com.google.gson.JsonArray
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.rabbitown.yalib.module.chat.text.event.HoverEvent
import com.rabbitown.yalib.module.chat.text.event.hover.Content
import com.rabbitown.yalib.module.chat.text.impl.PlainTextElement
import com.rabbitown.yalib.module.nms.base.chat.ChatBaseComponent
import net.md_5.bungee.api.chat.BaseComponent

/**
 * Represents a [Raw JSON Text](https://minecraft.gamepedia.com/Raw_JSON_text_format).
 *
 * @author Yoooooory
 */
class JSONText() : Content, ArrayList<JSONTextElement>() {

    constructor(vararg elements: JSONTextElement) : this() {
        addAll(elements)
    }

    override fun requiredAction() = HoverEvent.Action.SHOW_TEXT

    fun toJsonTree(): JsonArray {
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

    fun toNMS() = ChatBaseComponent.newInstance(this)

    /** Convert to [BaseComponent]. */
    fun toMd5BaseComponent(): BaseComponent = TODO()

    companion object {
        @JvmStatic fun serialize(origin: String): JSONText = TODO()
    }

}