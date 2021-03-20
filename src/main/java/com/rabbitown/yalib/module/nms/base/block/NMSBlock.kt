package com.rabbitown.yalib.module.nms.base.block

import com.rabbitown.yalib.module.nms.NMSBase
import com.rabbitown.yalib.module.nms.NMSManager
import com.rabbitown.yalib.module.nms.base.SoundEffectType
import com.rabbitown.yalib.util.ReflectUtil.Companion.access
import com.rabbitown.yalib.util.ReflectUtil.Companion.getMethodByName
import org.bukkit.block.Block

/**
 * @author Milkory
 */
abstract class NMSBlock(block: Block) : NMSBase {

    override val nms: Any = craftClazz.getDeclaredMethod("getNMSBlock").access().invoke(block)

    /** Get sound effects of the block. */
    fun getSoundEffects() = SoundEffectType(
        clazz.getMethodByName("getStepSound").access().invoke(nms, clazz.getMethod("getBlockData").invoke(nms))
    )

    fun getStepSound() = getSoundEffects()

    companion object {
        val clazz = NMSManager.getNMSClass("Block")
        val craftClazz = NMSManager.getCraftClass("block.CraftBlock")
        @JvmStatic fun get(block: Block) = object : NMSBlock(block) {}
        fun Block.asNMS() = get(this)
    }
}