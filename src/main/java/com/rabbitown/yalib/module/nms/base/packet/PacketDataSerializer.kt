package com.rabbitown.yalib.module.nms.base.packet

import com.rabbitown.yalib.module.nms.NMSBase
import com.rabbitown.yalib.module.nms.NMSManager
import io.netty.buffer.ByteBuf

/**
 * @author Yoooooory
 */
abstract class PacketDataSerializer(byteBuf: ByteBuf) : NMSBase {

    override val nms = clazz.getConstructor(ByteBuf::class.java).newInstance(byteBuf) as ByteBuf

    companion object {
        val clazz = NMSManager.getNMSClass("PacketDataSerializer")
        fun newInstance(byteBuf: ByteBuf) = object : PacketDataSerializer(byteBuf) {}
    }
}