package com.rabbitown.yalib.module.nms.base.packet.impl

import com.rabbitown.yalib.module.nms.NMSManager
import com.rabbitown.yalib.module.nms.NMSVersion
import com.rabbitown.yalib.module.nms.base.chat.ChatBaseComponent
import com.rabbitown.yalib.module.nms.base.chat.ChatMessageType
import com.rabbitown.yalib.module.nms.base.packet.Packet
import com.rabbitown.yalib.module.nms.base.packet.PacketDataSerializer
import com.rabbitown.yalib.util.SystemUtil
import java.util.*

/**
 * @author Yoooooory
 */
abstract class PacketPlayOutChat(
    component: ChatBaseComponent,
    type: ChatMessageType,
    uuid: UUID = SystemUtil.nullUUID
) : Packet {

    override val nms: Any = clazz.let {
        val constructor = it.constructors.first { con -> con.parameterCount > 0 }
        if (NMSVersion.CURRENT.isAfter(NMSVersion.V1_16_R1)) constructor.newInstance(component.nms, type.nms, uuid)
        else constructor.newInstance(component.nms, type.nms)
    }

    override fun read(data: PacketDataSerializer) {
        clazz.getMethod("a").invoke(nms, data.nms)
    }

    override fun write(data: PacketDataSerializer) {
        clazz.getMethod("b").invoke(nms, data.nms)
    }

    fun getMessageType() = ChatMessageType.of(
        clazz.getMethod(if (NMSVersion.CURRENT.isAfter(NMSVersion.V1_13_R1)) "d" else "c").invoke(nms)
    )

    fun isFromSystem(): Boolean {
        val type = getMessageType()
        return type == ChatMessageType.SYSTEM || type == ChatMessageType.GAME_INFO
    }

    override fun a() = true

    companion object {
        val clazz = NMSManager.getNMSClass("PacketPlayOutChat")

        fun newInstance(component: ChatBaseComponent, type: ChatMessageType, uuid: UUID?) =
            object : PacketPlayOutChat(component, type, uuid ?: SystemUtil.nullUUID) {}

        fun newInstance(component: ChatBaseComponent, type: ChatMessageType) =
            newInstance(component, type, SystemUtil.nullUUID)
    }
}