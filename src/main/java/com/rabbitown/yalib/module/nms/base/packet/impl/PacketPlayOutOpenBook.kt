package com.rabbitown.yalib.module.nms.base.packet.impl

import com.rabbitown.yalib.module.nms.NMSManager
import com.rabbitown.yalib.module.nms.base.enum.EnumHand
import com.rabbitown.yalib.module.nms.base.packet.Packet
import com.rabbitown.yalib.util.ReflectUtil.Companion.access
import com.rabbitown.yalib.util.ReflectUtil.Companion.getConstructorHasParams

/**
 * @author Yoooooory
 */
class PacketPlayOutOpenBook(
    hand: EnumHand
) : Packet {

    override val nms: Any = constructor.newInstance(hand.nms)

    companion object {
        val clazz = NMSManager.getNMSClass("PacketPlayOutOpenBook")
        private val constructor = clazz.getConstructorHasParams().access()
    }
}