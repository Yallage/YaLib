package com.rabbitown.yalib.module.chat.text.event

/**
 * @author Yoooooory
 */
class ClickEvent(val action: Action, val value: String) : JSONTextEvent {
    enum class Action {
        OPEN_URL, OPEN_FILE, RUN_COMMAND, SUGGEST_COMMAND, CHANGE_PAGE, COPY_TO_CLIPBOARD;

        override fun toString() = super.toString().toLowerCase()
    }
}