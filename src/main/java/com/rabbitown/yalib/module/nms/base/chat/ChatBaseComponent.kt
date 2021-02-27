package com.rabbitown.yalib.module.nms.base.chat

import com.google.gson.JsonElement
import com.rabbitown.yalib.module.chat.text.JSONText
import com.rabbitown.yalib.module.nms.NMSBase
import com.rabbitown.yalib.module.nms.NMSManager
import com.rabbitown.yalib.util.ReflectUtil.Companion.access

/**
 * @author Milkory
 */
abstract class ChatBaseComponent(val text: JSONText) : NMSBase {
    override val nms: Any = serializerClazz.getMethod("a", JsonElement::class.java).access().invoke(null, text.toJsonTree())
    companion object {
        val clazz = NMSManager.getNMSClass("ChatBaseComponent")
        val serializerClazz = NMSManager.getNMSClass("IChatBaseComponent\$ChatSerializer")
        fun newInstance(text: JSONText) = object : ChatBaseComponent(text) {}
    }
}