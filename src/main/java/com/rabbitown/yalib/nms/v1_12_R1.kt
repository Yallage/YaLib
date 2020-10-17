package com.rabbitown.yalib.nms

import net.minecraft.server.v1_12_R1.*
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

/**
 * @author Yoooooory
 */
class v1_12_R1 : NMSBase {

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
        nbt.c().forEach { map[it] = fromNBTValue(nbt[it]) }
        return map
    }

    private fun toNBTValue(obj: Any): NBTBase = when (obj) {
        is Byte -> NBTTagByte(obj)
        is Short -> NBTTagShort(obj)
        is Int -> NBTTagInt(obj)
        is Long -> NBTTagLong(obj)
        is Float -> NBTTagFloat(obj)
        is Double -> NBTTagDouble(obj)
        is ByteArray -> NBTTagByteArray(obj)
        is String -> NBTTagString(obj)
        is List<*> -> NBTTagList().apply { obj.forEach { add(toNBTValue(it!!)) } }
        is Map<*, *> -> TODO("Not yet implemented")
        is IntArray -> NBTTagIntArray(obj)
        is LongArray -> NBTTagLongArray(obj)
        else -> error("Invalid NBT value type.")
    }

    private fun fromNBTValue(obj: NBTBase): Any = when (obj.typeId.toInt()) {
        1 -> (obj as NBTTagByte).g()
        2 -> (obj as NBTTagShort).f()
        3 -> (obj as NBTTagInt).e()
        4 -> (obj as NBTTagLong).d()
        5 -> (obj as NBTTagFloat).i()
        6 -> (obj as NBTTagDouble).asDouble()
        7 -> (obj as NBTTagByteArray).c()
        8 -> (obj as NBTTagString).c_()
        9 -> TODO("Not yet implemented")
        10 -> TODO("Not yet implemented")
        11 -> (obj as NBTTagIntArray).d()
        12 -> TODO("Not yet implemented")
        else -> error("Invalid NBT value type.")
    }

}