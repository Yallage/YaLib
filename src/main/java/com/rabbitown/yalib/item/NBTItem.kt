package com.rabbitown.yalib.item

import com.rabbitown.yalib.YaLib
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * @author Yoooooory
 */
class NBTItem {

    var stack: ItemStack
        private set

    constructor(itemStack: ItemStack) {
        this.stack = itemStack
    }

    constructor(type: Material) : this(ItemStack(type))
    constructor(type: Material, amount: Int) : this(ItemStack(type, amount))

    fun getNBTTag(key: String) = YaLib.NMS.getNBTTag(stack, key)

    fun setNBTTag(key: String, obj: Any) {
        stack = YaLib.NMS.setNBTTag(stack, key, obj)
    }

    fun removeNBTTag(key: String) {
        stack = YaLib.NMS.removeNBTTag(stack, key)
    }

}