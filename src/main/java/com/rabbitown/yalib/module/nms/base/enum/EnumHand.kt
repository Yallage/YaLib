package com.rabbitown.yalib.module.nms.base.enum

import com.rabbitown.yalib.module.nms.NMSBase
import com.rabbitown.yalib.module.nms.NMSManager
import com.rabbitown.yalib.module.nms.base.chat.ChatMessageType

/**
 * @author Milkory
 */
enum class EnumHand : NMSBase {
    MAIN_HAND, OFF_HAND;

    override val nms: Any
        get() = clazz.enumConstants[ordinal]

    companion object {
        val clazz = NMSManager.getNMSClass("EnumHand")
        fun of(obj: Any) = if (clazz.isInstance(obj)) ChatMessageType.valueOf((obj as Enum<*>).name) else error("Type mismatch.")
    }
}