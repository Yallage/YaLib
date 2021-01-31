package com.rabbitown.yalib.module.chat.text

import com.google.gson.JsonArray
import com.rabbitown.yalib.module.chat.text.event.HoverEvent
import com.rabbitown.yalib.module.chat.text.event.hover.Content
import net.md_5.bungee.api.chat.BaseComponent

/**
 * Represents a [Raw JSON Text](https://minecraft.gamepedia.com/Raw_JSON_text_format).
 *
 * @author Yoooooory
 */
class JSONText(val elements: List<JSONTextElement>) : Content {

    override fun requiredAction() = HoverEvent.Action.SHOW_TEXT

    fun toJsonTree() = JsonArray().apply { elements.forEach { add(it.toJsonTree()) } }

    override fun toString(): String = TODO()

    fun toNMS(): Nothing = TODO()

    fun toBaseComponent(): BaseComponent = TODO()

    companion object {
        @JvmStatic fun serialize(origin: String): JSONText = TODO()
    }

}