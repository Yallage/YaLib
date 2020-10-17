package com.rabbitown.yalib.item

import com.rabbitown.yalib.YaLib
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Represents a NBT item.
 *
 * @author Yoooooory
 */
class NBTItem {

    /**
     * The original [ItemStack] of the item.
     */
    var stack: ItemStack
        private set

    constructor(itemStack: ItemStack) {
        this.stack = itemStack
    }

    constructor(type: Material) : this(ItemStack(type))
    constructor(type: Material, amount: Int) : this(ItemStack(type, amount))

    /**
     * Get a nbt tag of a specify key.
     */
    fun getNBTTag(key: String) = YaLib.NMS.getNBTTag(stack, key)

    /**
     * Get all nbt tags in string format.
     */
    fun getAllNBTTag() = YaLib.NMS.getAllNBTTag(stack)

    /**
     * Get the map of all nbt tags.
     */
    fun getAllNBTTagMap() = YaLib.NMS.getAllNBTTagMap(stack)

    /**
     * Set a NBT tag for the item.
     */
    fun setNBTTag(key: String, obj: Any) {
        stack = YaLib.NMS.setNBTTag(stack, key, obj)
    }

    /**
     * Remove a NBT tag from the item.
     */
    fun removeNBTTag(key: String) {
        stack = YaLib.NMS.removeNBTTag(stack, key)
    }

}