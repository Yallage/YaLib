package com.rabbitown.yalib.module.nms.base.packet

import com.rabbitown.yalib.module.nms.NMSBase

/**
 * @author Yoooooory
 */
interface Packet : NMSBase {
    fun write(data: PacketDataSerializer)
    fun read(data: PacketDataSerializer)

    /** *(unknown)*
     *  @since v1_13_R2 */
    fun a() = false
}