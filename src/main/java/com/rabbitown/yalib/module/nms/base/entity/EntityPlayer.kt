package com.rabbitown.yalib.module.nms.base.entity

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

    override val nms: Any = clazz.cast(entity)
    val playerConnection: PlayerConnection = PlayerConnection.ofNMSPlayer(nms)

    fun sendMessage(vararg components: ChatBaseComponent) = sendMessage(ChatMessageType.SYSTEM, *components)

    fun sendMessage(type: ChatMessageType, vararg components: ChatBaseComponent) =
        components.forEach { sendMessage(type, SystemUtil.nullUUID, it) }

    fun sendMessage(type: ChatMessageType, uuid: UUID?, component: ChatBaseComponent) {
        playerConnection.sendPacket(PacketPlayOutChat.newInstance(component, type, uuid))
    }

    companion object {
        val clazz = NMSManager.getNMSClass("EntityPlayer")
        fun newInstance(entity: Player) = object : EntityPlayer(entity) {}
    }
}