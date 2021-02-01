package com.rabbitown.yalib.module.chat.text.event

import com.google.gson.annotations.SerializedName

/**
 * @author Yoooooory
 */
class ClickEvent(val action: Action, val value: String) : JSONTextEvent {
    enum class Action {
        @SerializedName("open_url")
        OPEN_URL,

        @SerializedName("open_file")
        OPEN_FILE,

        @SerializedName("run_command")
        RUN_COMMAND,

        @SerializedName("suggest_command")
        SUGGEST_COMMAND,

        @SerializedName("change_page")
        CHANGE_PAGE,

        @SerializedName("copy_to_clipboard")
        COPY_TO_CLIPBOARD;

        override fun toString() = super.toString().toLowerCase()
    }
}