package com.rabbitown.yalib.module.chat.text

import com.google.gson.JsonElement
import com.rabbitown.yalib.module.nms.base.chat.ChatBaseComponent

/**
 * @author Yoooooory
 */
interface IJSONTextElement {
    fun toNMS(): ChatBaseComponent
    fun toJsonTree(): JsonElement
}