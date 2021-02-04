package com.rabbitown.yalib.module.chat.text

import com.google.gson.JsonElement
import com.rabbitown.yalib.module.chat.text.event.HoverEvent
import com.rabbitown.yalib.module.chat.text.event.hover.Content
import com.rabbitown.yalib.module.nms.base.chat.ChatBaseComponent

/**
 * @author Yoooooory
 */
interface IJSONTextElement : Content {
    override fun requiredAction() = HoverEvent.Action.SHOW_TEXT
    fun toNMS(): ChatBaseComponent = ChatBaseComponent.newInstance(JSONText(this))
    fun toJsonTree(): JsonElement
}