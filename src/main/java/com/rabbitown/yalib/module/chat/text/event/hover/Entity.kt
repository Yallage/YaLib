package com.rabbitown.yalib.module.chat.text.event.hover

import com.rabbitown.yalib.module.chat.text.JSONText
import com.rabbitown.yalib.module.chat.text.event.HoverEvent
import com.rabbitown.yalib.module.item.NBTItem
import com.rabbitown.yalib.util.ExtendFunction.Companion.escape
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * @author Yoooooory
 */
class Entity(
        val type: EntityType,
        val id: UUID,
        val name: JSONText? = null
) : Content {

    // Constructor for Java.
    constructor(type: EntityType, id: UUID) : this(type, id, null)

    constructor(entity: Entity) : this(entity.type, entity.uniqueId, JSONText.serialize(entity.customName ?: ""))

    override fun requiredAction() = HoverEvent.Action.SHOW_ENTITY

    override fun toString() = """{name:"${name.toString().escape()}",type:"${type.key}",id:$id}"""

}