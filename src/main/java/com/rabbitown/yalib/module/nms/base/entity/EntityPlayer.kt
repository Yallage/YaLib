package com.rabbitown.yalib.module.nms.base.entity

import com.rabbitown.yalib.module.chat.text.JSONText
import com.rabbitown.yalib.module.chat.text.JSONTextElement
import com.rabbitown.yalib.module.nms.NMSBase
import com.rabbitown.yalib.module.nms.NMSManager
import com.rabbitown.yalib.module.nms.base.chat.ChatBaseComponent
import com.rabbitown.yalib.module.nms.base.chat.ChatMessageType
import com.rabbitown.yalib.module.nms.base.packet.impl.PacketPlayOutChat
import com.rabbitown.yalib.module.nms.base.player.PlayerConnection
import com.rabbitown.yalib.util.SystemUtil
import org.bukkit.entity.Player
import java.util.*

/**
 * Represents an EntityPlayer.
 *
 * @author Yoooooory
 */
abstract class EntityPlayer(entity: Player) : NMSBase {

    override val nms: Any = craftClazz.getMethod("getHandle").invoke(entity)
    val playerConnection: PlayerConnection = PlayerConnection.ofNMSPlayer(nms)

    fun sendMessage(vararg element: JSONTextElement) = sendMessage(JSONText(*element))
    fun sendMessage(vararg text: JSONText) = sendMessage(ChatMessageType.SYSTEM, *text)

    fun sendMessage(type: ChatMessageType, vararg text: JSONText) =
        text.forEach { sendMessage(type, SystemUtil.nullUUID, it) }

    fun sendMessage(type: ChatMessageType, uuid: UUID?, text: JSONText) {
        playerConnection.sendPacket(PacketPlayOutChat.newInstance(text.toNMS(), type, uuid))
    }

    companion object {
        val clazz = NMSManager.getNMSClass("EntityPlayer")
        val craftClazz = NMSManager.getCraftClass("entity.CraftPlayer")
        @JvmStatic fun newInstance(entity: Player) = object : EntityPlayer(entity) {}
        fun Player.asNMS() = newInstance(this)
    }
}