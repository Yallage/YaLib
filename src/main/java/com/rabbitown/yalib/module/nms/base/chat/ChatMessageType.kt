package com.rabbitown.yalib.module.nms.base.chat

import com.rabbitown.yalib.module.nms.NMSBase
import com.rabbitown.yalib.module.nms.NMSManager

/**
 * @author Yoooooory
 */
enum class ChatMessageType : NMSBase {
    CHAT, SYSTEM, GAME_INFO;

    override val nms: Any
        get() = clazz.enumConstants[ordinal]

    companion object {
        val clazz = NMSManager.getNMSClass("ChatMessageType")
        fun of(obj: Any) = if (clazz.isInstance(obj)) valueOf((obj as Enum<*>).name) else error("Type mismatch.")
    }
}