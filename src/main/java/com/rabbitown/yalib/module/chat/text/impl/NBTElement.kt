package com.rabbitown.yalib.module.chat.text.impl

import com.rabbitown.yalib.module.chat.text.JSONTextElement

/**
 * @author Yoooooory
 */
internal open class NBTElement(val nbt: String, val interpret: String) : JSONTextElement() {
    class Block(nbt: String, interpret: String, val block: String) : NBTElement(nbt, interpret)
    class Entity(nbt: String, interpret: String, val entity: String) : NBTElement(nbt, interpret)
    class Storage(nbt: String, interpret: String, val storage: String) : NBTElement(nbt, interpret)
}