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
    var itemStack: ItemStack
        private set

    constructor(itemStack: ItemStack) {
        this.itemStack = itemStack
    }

    constructor(type: Material) : this(ItemStack(type))
    constructor(type: Material, amount: Int) : this(ItemStack(type, amount))

    /**
     * Check whether the NBT has a key.
     */
    fun hasNBTTag(key: String) = YaLib.NMS.hasNBTTag(itemStack, key)

    /**
     * Get a nbt tag of a specify key.
     */
    fun getNBTTag(key: String) = YaLib.NMS.getNBTTag(itemStack, key)

    /**
     * Get all nbt tags in string format.
     */
    fun getAllNBTTag() = YaLib.NMS.getAllNBTTag(itemStack)

    /**
     * Get the map of all nbt tags.
     */
    fun getAllNBTTagMap() = YaLib.NMS.getAllNBTTagMap(itemStack)

    /**
     * Set a NBT tag for the item.
     */
    fun setNBTTag(key: String, obj: Any) {
        itemStack = YaLib.NMS.setNBTTag(itemStack, key, obj)
    }

    /**
     * Remove a NBT tag from the item.
     */
    fun removeNBTTag(key: String) {
        itemStack = YaLib.NMS.removeNBTTag(itemStack, key)
    }

}