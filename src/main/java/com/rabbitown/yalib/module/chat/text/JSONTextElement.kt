package com.rabbitown.yalib.module.chat.text

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.rabbitown.yalib.module.chat.text.event.*
import com.rabbitown.yalib.module.chat.text.format.JSONTextFormat

/**
 * @author Yoooooory
 */
abstract class JSONTextElement {

    @Transient val format = JSONTextFormat()
    var clickEvent: ClickEvent? = null
    var hoverEvent: HoverEvent? = null
    var insertion: InsertEvent? = null

    open fun toJsonTree() = Gson().toJsonTree(this).asJsonObject.apply { addAll(format.toJsonObject()) }

    private fun JsonObject.addAll(value: JsonObject) = value.entrySet().forEach { this.add(it.key, it.value) }

}