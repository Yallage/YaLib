package com.rabbitown.yalib.module.chat.text.impl

import com.rabbitown.yalib.module.chat.text.JSONTextElement

/**
 * @author Milkory
 */
sealed class NBTElement(val nbt: String, val interpret: Boolean? = null) : JSONTextElement() {
    class Block(nbt: String, interpret: Boolean? = null, val block: String) : NBTElement(nbt, interpret)
    class Entity(nbt: String, interpret: Boolean? = null, val entity: String) : NBTElement(nbt, interpret)
    class Storage(nbt: String, interpret: Boolean? = null, val storage: String) : NBTElement(nbt, interpret)
}