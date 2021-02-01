package com.rabbitown.yalib.module.chat.text.event.hover

import com.rabbitown.yalib.module.chat.text.event.HoverEvent

/**
 * @author Yoooooory
 */
interface Content {
    fun requiredAction(): HoverEvent.Action
}