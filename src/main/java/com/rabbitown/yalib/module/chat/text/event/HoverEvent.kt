package com.rabbitown.yalib.module.chat.text.event

import com.rabbitown.yalib.module.chat.text.event.hover.Content

/**
 * @author Yoooooory
 */
class HoverEvent(val action: Action, val contents: Content) : JSONTextEvent {

    init {
        check(contents.requiredAction() == action) { "The contents are not fit the action." }
    }

    enum class Action {
        SHOW_TEXT, SHOW_ITEM, SHOW_ENTITY
    }

}