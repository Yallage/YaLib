package com.rabbitown.yalib.module.chat.text.event.hover

import com.rabbitown.yalib.module.chat.text.event.HoverEvent
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * @author Milkory
 */
class Item(
        val id: String,
        val count: Int? = null,
        val tag: String? = null
) : Content {

    // Constructors for Java.
    constructor(id: String) : this(id, null)
    constructor(id: String, count: Int) : this(id, count, null)

    constructor(type: Material, count: Int, tag: String) : this(type.key.toString(), count, tag)

//    /** TODO: Not supported yet. */
//    internal constructor(item: ItemStack) : this(item.type, item.amount, NBTItem(item).getAllNBTTag())

    override fun requiredAction() = HoverEvent.Action.SHOW_ITEM

    override fun toString() = """{id:"$id",count:$count,tag:$tag}"""

}