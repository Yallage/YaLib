package com.rabbitown.yalib.module.nms.base

import com.rabbitown.yalib.module.nms.NMSBase
import com.rabbitown.yalib.module.nms.NMSManager
import com.rabbitown.yalib.util.ReflectUtil.Companion.access

/**
 * @author Yoooooory
 */
class MinecraftKey internal constructor(
    override val nms: Any
) : NMSBase {

    val key = clazz.getMethod("getKey").access().invoke(nms) as String

    companion object {
        val clazz = NMSManager.getNMSClass("MinecraftKey")
    }
}