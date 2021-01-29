package com.rabbitown.yalib.module.item

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Represents a NBT item.
 *
 * @author Yoooooory
 */
@Deprecated("Awaiting for refactor.")
class NBTItem : ItemStack {

    constructor() : super()
    constructor(stack: ItemStack) : super(stack)
    constructor(type: Material) : super(type)
    constructor(type: Material, amount: Int) : super(type, amount)

    /** Check whether the item has the NBT with the specify key. */
    fun hasNBTTag(key: String): Boolean = TODO()

    /** Get the NBT value of the key. */
    fun getNBTTag(key: String): Any = TODO()

    /** Set a NBT tag to the value. */
    fun setNBTTag(key: String, value: Any): Boolean = TODO()

    /** Remove a NBT tag of the key. */
    fun removeNBTTag(key: String): Boolean = TODO()

    /** Get all the NBT tag of the item, formatted as a String. */
    fun getAllNBTTag(): String = TODO()

    /** Get a map contains all the nbt tags. */
    fun getAllNBTTagMap(): Map<String, Any> = TODO()

}