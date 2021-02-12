package com.rabbitown.yalib.module.nms.base.packet.impl

import com.rabbitown.yalib.module.nms.NMSManager
import com.rabbitown.yalib.module.nms.NMSVersion
import com.rabbitown.yalib.module.nms.base.chat.ChatBaseComponent
import com.rabbitown.yalib.module.nms.base.chat.ChatMessageType
import com.rabbitown.yalib.module.nms.base.packet.Packet
import com.rabbitown.yalib.util.ReflectUtil.Companion.access
import com.rabbitown.yalib.util.ReflectUtil.Companion.getConstructorHasParams
import com.rabbitown.yalib.util.SystemUtil
import java.util.*

/**
 * @author Yoooooory
 */
class PacketPlayOutChat(
    component: ChatBaseComponent,
    val type: ChatMessageType,
    uuid: UUID? = SystemUtil.nullUUID
) : Packet {

    constructor(component: ChatBaseComponent, type: ChatMessageType) : this(component, type, SystemUtil.nullUUID)

    override val nms: Any = clazz.let {
        if (NMSVersion.CURRENT.isAfter(NMSVersion.V1_16_R1)) constructor.newInstance(component.nms, type.nms, uuid ?: SystemUtil.nullUUID)
        else constructor.newInstance(component.nms, type.nms)
    }

    fun isFromSystem() = type == ChatMessageType.SYSTEM || type == ChatMessageType.GAME_INFO

    companion object {
        val clazz = NMSManager.getNMSClass("PacketPlayOutChat")
        private val constructor = clazz.getConstructorHasParams().access()
    }
}