package com.rabbitown.yalib.module.nms.base.chat

import com.rabbitown.yalib.module.nms.NMSBase
import com.rabbitown.yalib.module.nms.NMSManager

/**
 * @author Yoooooory
 */
abstract class ChatBaseComponent : NMSBase {
    companion object {
        val clazz = NMSManager.getNMSClass("ChatBaseComponent")
        fun newInstance(): Nothing = TODO()
    }
}