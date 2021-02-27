package com.rabbitown.yalib.module.nms.base

import com.rabbitown.yalib.ServerVersion
import com.rabbitown.yalib.module.nms.NMSBase
import com.rabbitown.yalib.module.nms.NMSManager
import com.rabbitown.yalib.module.nms.NMSVersion
import com.rabbitown.yalib.util.ReflectUtil.Companion.access
import org.bukkit.Sound

/**
 * @author Yoooooory
 */
class SoundEffectType internal constructor(
    override val nms: Any
) : NMSBase {

    fun getStepSound(): Sound {
        return versionDiff("d", "getStepSound")
    }

    fun getPlaceSound(): Sound {
        return versionDiff("e", "getPlaceSound")
    }

    fun getFallSound(): Sound {
        return versionDiff("g", "getFallSound")
    }

    fun getBreakSound(): Sound {
        return if (NMSVersion.CURRENT.isAfter(NMSVersion.V1_16_R2)) {
            getSound(clazz.getMethod("getBreakSound").access().invoke(nms))
        } else versionDiff("o", "q", "y", "z", "X")
    }

    fun getHitSound(): Sound {
        return if (NMSVersion.CURRENT.isAfter(NMSVersion.V1_16_R2)) {
            getSound(clazz.getMethod("getHitSound").access().invoke(nms))
        } else versionDiff("r", "t", "B", "C", "aa")
    }

    private fun versionDiff(obfuscated: String, real: String): Sound {
        // Your mother 1.16.4's x() to 1.16.5's getXXXXSound() don't change NMS version? Both 1_16_R3? NMSL.
        val method = if (ServerVersion.CURRENT >= ServerVersion.V1_16_5) real else obfuscated
        return getSound(clazz.getMethod(method).access().invoke(nms))
    }

    private fun versionDiff(a: String, b: String, c: String, d: String, e: String): Sound {
        val field = when (NMSVersion.CURRENT) {
            NMSVersion.V1_12_R1 -> a
            NMSVersion.V1_13_R1, NMSVersion.V1_13_R2 -> b
            NMSVersion.V1_14_R1 -> c
            NMSVersion.V1_15_R1 -> d
            NMSVersion.V1_16_R1 -> e
            else -> throw UnsupportedOperationException()
        }
        return getSound(clazz.getDeclaredField(field).access().get(nms))
    }

    private fun getSound(soundEffect: Any): Sound {
        return Sound.valueOf(MinecraftKey(keyField.get(soundEffect)).key.replace(".", "_").toUpperCase())
    }

    companion object {
        val clazz = NMSManager.getNMSClass("SoundEffectType")
        private val keyField = NMSManager.getNMSClass("SoundEffect").getDeclaredField("b").access()
    }
}