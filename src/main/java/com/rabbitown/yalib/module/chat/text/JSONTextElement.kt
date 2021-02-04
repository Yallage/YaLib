package com.rabbitown.yalib.module.chat.text

import com.google.gson.JsonObject
import com.rabbitown.yalib.module.chat.text.event.ClickEvent
import com.rabbitown.yalib.module.chat.text.event.HoverEvent
import com.rabbitown.yalib.module.chat.text.event.InsertEvent
import com.rabbitown.yalib.module.chat.text.format.JSONTextFormat
import com.rabbitown.yalib.util.SystemUtil

/**
 * @author Yoooooory
 */
abstract class JSONTextElement : IJSONTextElement {

    @Transient val format = JSONTextFormat()
    var clickEvent: ClickEvent? = null
    var hoverEvent: HoverEvent? = null
    var insertion: InsertEvent? = null

    override fun toJsonTree(): JsonObject = SystemUtil.gson.toJsonTree(this).asJsonObject.apply { addAll(format.toJsonObject()) }

    private fun JsonObject.addAll(value: JsonObject) = value.entrySet().forEach { this.add(it.key, it.value) }

}