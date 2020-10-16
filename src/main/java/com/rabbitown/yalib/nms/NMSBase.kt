package com.rabbitown.yalib.nms

import org.bukkit.inventory.ItemStack

/**
 * @author Yoooooory
 */
interface NMSBase {

    fun hasNBTTag(item: ItemStack, key: String): Boolean

    fun getNBTTag(item: ItemStack, key: String): Any

    fun setNBTTag(item: ItemStack, key: String, obj: Any): ItemStack

    fun removeNBTTag(item: ItemStack, key: String): ItemStack

}