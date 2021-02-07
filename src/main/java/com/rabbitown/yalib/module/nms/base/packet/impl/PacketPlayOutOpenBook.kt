package com.rabbitown.yalib.module.nms.base.packet.impl

import com.rabbitown.yalib.module.nms.NMSManager
import com.rabbitown.yalib.module.nms.base.enum.EnumHand
import com.rabbitown.yalib.module.nms.base.packet.Packet

/**
 * @author Yoooooory
 */
abstract class PacketPlayOutOpenBook(
    hand: EnumHand
) : Packet {

    override val nms: Any = constructor.newInstance(hand.nms)

    companion object {
        val clazz = NMSManager.getNMSClass("PacketPlayOutOpenBook")
        private val constructor = clazz.constructors.first { it.parameterCount > 0 }

        fun newInstance(hand: EnumHand) =
            object : PacketPlayOutOpenBook(hand) {}
    }
}