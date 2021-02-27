package com.rabbitown.yalib.module.nms.base.entity

import com.rabbitown.yalib.module.chat.text.IJSONTextElement
import com.rabbitown.yalib.module.nms.NMSBase
import com.rabbitown.yalib.module.nms.NMSManager
import com.rabbitown.yalib.module.nms.base.chat.ChatMessageType
import com.rabbitown.yalib.module.nms.base.entity.player.PlayerConnection
import com.rabbitown.yalib.module.nms.base.enum.EnumHand
import com.rabbitown.yalib.module.nms.base.packet.impl.PacketPlayOutChat
import com.rabbitown.yalib.module.nms.base.packet.impl.PacketPlayOutOpenBook
import com.rabbitown.yalib.util.ReflectUtil.Companion.access
import com.rabbitown.yalib.util.SystemUtil
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta
import java.util.*

/**
 * Represents an EntityPlayer.
 *
 * @author Milkory
 */
abstract class EntityPlayer(val entity: Player) : NMSBase {

    override val nms: Any = craftClazz.getMethod("getHandle").access().invoke(entity)
    val playerConnection: PlayerConnection = PlayerConnection.ofNMSPlayer(nms)

    fun respawn() {
        TODO()
    }

    fun sendMessage(vararg text: IJSONTextElement) = sendMessage(ChatMessageType.SYSTEM, *text)

    fun sendMessage(type: ChatMessageType, vararg text: IJSONTextElement) =
        text.forEach { sendMessage(type, SystemUtil.nullUUID, it) }

    fun sendMessage(type: ChatMessageType, uuid: UUID?, text: IJSONTextElement) {
        playerConnection.sendPacket(PacketPlayOutChat(text.toNMS(), type, uuid))
    }

    fun sendActionbar(vararg text: IJSONTextElement) =
        text.forEach { sendActionbar(SystemUtil.nullUUID, it) }

    fun sendActionbar(uuid: UUID?, text: IJSONTextElement): Unit = TODO()

    fun openBook(book: ItemStack) {
        check(book.itemMeta is BookMeta)
        val inv = entity.inventory
        val old = inv.itemInMainHand
        inv.setItemInMainHand(book)
        openBook(EnumHand.MAIN_HAND)
        inv.setItemInMainHand(old)
    }

    fun openBook(hand: EnumHand) {
        playerConnection.sendPacket(PacketPlayOutOpenBook(hand))
    }

    companion object {
        val clazz = NMSManager.getNMSClass("EntityPlayer")
        val craftClazz = NMSManager.getCraftClass("entity.CraftPlayer")
        @JvmStatic fun get(entity: Player) = object : EntityPlayer(entity) {}
        fun Player.asNMS() = get(this)
    }
}