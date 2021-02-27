package com.rabbitown.yalib.module.nms

import org.bukkit.Bukkit

/**
 * Represents the NMS version.
 *
 * @author Yoooooory
 */
@Suppress("unused")
enum class NMSVersion {
    // Unsupported versions
//        V1_8_R1, V1_8_R2, V1_8_R3,
//        V1_9_R1, V1_9_R2,
//        V1_10_R1,
//        V1_11_R1,
    V1_12_R1,
    V1_13_R1, V1_13_R2,
    V1_14_R1,
    V1_15_R1,
    V1_16_R1, V1_16_R2, V1_16_R3;

    override fun toString() = this.name.replaceRange(0..0, "v")
    fun isBefore(ver: NMSVersion) = ordinal <= ver.ordinal
    fun isAfter(ver: NMSVersion) = ordinal >= ver.ordinal

    companion object {
        /** Current server NMS version. */
        val CURRENT = valueOf(Bukkit.getServer()::class.java.`package`.name.substring(23).toUpperCase())
    }
}