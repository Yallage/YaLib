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
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.chat.ComponentSerializer

/**
 * Represents a [Raw JSON Text](https://minecraft.gamepedia.com/Raw_JSON_text_format).
 *
 * @author Yoooooory
 */
class JSONText() : ArrayList<IJSONTextElement>(), Content, IJSONTextElement {

    constructor(vararg elements: JSONTextElement) : this() {
        addAll(elements)
    }

    override fun requiredAction() = HoverEvent.Action.SHOW_TEXT

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
    }

}