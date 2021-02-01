package com.rabbitown.yalib.module.chat.text.event

import com.google.gson.annotations.SerializedName
import com.rabbitown.yalib.module.chat.text.event.hover.Content

/**
 * @author Yoooooory
 */
class HoverEvent(val action: Action, val contents: Content) : JSONTextEvent {

    init {
        check(contents.requiredAction() == action) { "The contents are not fit the action." }
    }

    enum class Action {
        @SerializedName("show_text")
        SHOW_TEXT,

        @SerializedName("show_item")
        SHOW_ITEM,

        @SerializedName("show_entity")
        SHOW_ENTITY
    }

}