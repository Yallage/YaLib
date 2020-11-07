package com.rabbitown.yalib.nms

import net.minecraft.server.v1_16_R3.*
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

/**
 * @author Yoooooory
 */
class v1_16_R3 : NMSBase {

    override fun hasNBTTag(item: ItemStack, key: String) = CraftItemStack.asNMSCopy(item).tag?.hasKey(key) ?: false

    override fun getNBTTag(item: ItemStack, key: String) =
        fromNBTValue((CraftItemStack.asNMSCopy(item).tag ?: NBTTagCompound()).get(key))

    override fun setNBTTag(item: ItemStack, key: String, value: Any): ItemStack {
        val stack = CraftItemStack.asNMSCopy(item)
        stack.tag = (stack.tag ?: NBTTagCompound()).apply { set(key, toNBTValue(value)) }
        return CraftItemStack.asBukkitCopy(stack)
    }

    override fun removeNBTTag(item: ItemStack, key: String): ItemStack {
        val stack = CraftItemStack.asNMSCopy(item)
        stack.tag = (stack.tag ?: NBTTagCompound()).apply { remove(key) }
        return CraftItemStack.asBukkitCopy(stack)
    }

    override fun getAllNBTTag(item: ItemStack): String = CraftItemStack.asNMSCopy(item).tag.toString()

    override fun getAllNBTTagMap(item: ItemStack): Map<String, Any> {
        val nbt = CraftItemStack.asNMSCopy(item).tag ?: NBTTagCompound()
        val map = mutableMapOf<String, Any>()
        nbt.keys.forEach { map[it] = fromNBTValue(nbt[it]) }
        return map
    }

    private fun toNBTValue(obj: Any): NBTBase = when (obj) {
        is Byte -> NBTTagByte.a(obj)
        is Short -> NBTTagShort.a(obj)
        is Int -> NBTTagInt.a(obj)
        is Long -> NBTTagLong.a(obj)
        is Float -> NBTTagFloat.a(obj)
        is Double -> NBTTagDouble.a(obj)
        is ByteArray -> NBTTagByteArray(obj)
        is String -> NBTTagString.a(obj)
        is List<*> -> NBTTagList().apply { obj.forEach { add(toNBTValue(it!!)) } }
        is Map<*, *> -> NBTTagCompound().apply { obj.forEach { set(it.key as String, toNBTValue(it.value!!)) } }
        is IntArray -> NBTTagIntArray(obj)
        is LongArray -> NBTTagLongArray(obj)
        else -> error("Invalid NBT value type.")
    }

    private fun fromNBTValue(obj: NBTBase?): Any = when (obj!!.typeId.toInt()) {
        0 -> error("Unsupported NBT value type.")
        1 -> (obj as NBTTagByte).asByte()
        2 -> (obj as NBTTagShort).asShort()
        3 -> (obj as NBTTagInt).asInt()
        4 -> (obj as NBTTagLong).asLong()
        5 -> (obj as NBTTagFloat).asFloat()
        6 -> (obj as NBTTagDouble).asDouble()
        7 -> (obj as NBTTagByteArray).bytes
        8 -> (obj as NBTTagString).asString()
        9 -> mutableListOf<Any>().apply { (obj as NBTTagList).forEach { add(fromNBTValue(it)) } }
        10 -> mutableMapOf<String, Any>().apply {
            (obj as NBTTagCompound).map.forEach { set(it.key, fromNBTValue(it.value)) }
        }
        11 -> (obj as NBTTagIntArray).ints
        12 -> (obj as NBTTagLongArray).longs
        else -> error("Invalid NBT value type.")
    }

}