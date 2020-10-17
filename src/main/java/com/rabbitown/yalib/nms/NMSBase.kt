package com.rabbitown.yalib.nms

import org.bukkit.inventory.ItemStack

/**
 * @author Yoooooory
 */
interface NMSBase {

    fun hasNBTTag(item: ItemStack, key: String): Boolean

    fun getNBTTag(item: ItemStack, key: String): Any

    fun setNBTTag(item: ItemStack, key: String, value: Any): ItemStack

    fun removeNBTTag(item: ItemStack, key: String): ItemStack

    fun getAllNBTTag(item: ItemStack): String

    fun getAllNBTTagMap(item: ItemStack): Map<String, Any>

}