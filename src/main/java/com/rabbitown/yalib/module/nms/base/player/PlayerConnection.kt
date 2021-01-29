package com.rabbitown.yalib.module.nms.base.player

import com.rabbitown.yalib.module.nms.NMSBase
import com.rabbitown.yalib.module.nms.NMSManager
import com.rabbitown.yalib.module.nms.base.entity.EntityPlayer
import com.rabbitown.yalib.module.nms.base.packet.Packet
import com.rabbitown.yalib.util.ReflectUtil.Companion.getMethodByName

/**
 * @author Yoooooory
 */
abstract class PlayerConnection(override val nms: Any) : NMSBase {

    fun sendPacket(packet: Packet) {
        nms::class.java.getMethodByName("sendPacket").invoke(packet.nms)
    }

    companion object {
        val clazz = NMSManager.getNMSClass("PlayerConnection")
        fun ofNMSPlayer(nms: Any) = object : PlayerConnection(nms::class.java.getField("playerConnection").get(nms)) {}
        fun ofPlayer(player: EntityPlayer) = ofNMSPlayer(player.nms)
    }
}