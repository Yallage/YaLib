package com.rabbitown.yalib.item

import com.rabbitown.yalib.YaLib
import com.rabbitown.yalib.YaLib.Companion.NMS
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Represents a NBT item.
 *
 * @author Yoooooory
 */
class NBTItem : ItemStack {

    constructor() : super()
    constructor(stack: ItemStack) : super(stack)
    constructor(type: Material) : super(type)
    constructor(type: Material, amount: Int) : super(type, amount)

    /**
     * Check whether the item has the NBT with the specify key.
     */
    fun hasNBTTag(key: String) = NMS.hasNBTTag(this, key)

    /**
     * Get the NBT value of the key.
     */
    fun getNBTTag(key: String) = NMS.getNBTTag(this, key)

    /**
     * Set a NBT tag to the value.
     */
    fun setNBTTag(key: String, value: Any) {
        val item = NMS.setNBTTag(this, key, value)
        if (item.itemMeta != null) this.itemMeta = NMS.setNBTTag(this, key, value).itemMeta
    }

    /**
     * Remove a NBT tag of the key.
     */
    fun removeNBTTag(key: String) {
        val item = NMS.removeNBTTag(this, key)
        if (item.itemMeta != null) this.itemMeta = NMS.removeNBTTag(this, key).itemMeta
    }

    /**
     * Get all the NBT tag of the item, formatted as a String.
     */
    fun getAllNBTTag() = NMS.getAllNBTTag(this)

    /**
     * Get a map contains all the nbt tags.
     */
    fun getAllNBTTagMap() = NMS.getAllNBTTagMap(this)

}