package com.rabbitown.yalib.common.command

import com.rabbitown.yalib.YaLib
import com.rabbitown.yalib.module.chat.text.JSONText
import com.rabbitown.yalib.module.chat.text.event.HoverEvent
import com.rabbitown.yalib.module.chat.text.impl.PlainTextElement
import com.rabbitown.yalib.module.command.CommandSenderType
import com.rabbitown.yalib.module.command.SimpleCommandRemote
import com.rabbitown.yalib.module.command.annotation.*
import com.rabbitown.yalib.module.nms.base.entity.EntityPlayer
import com.rabbitown.yalib.module.nms.base.entity.EntityPlayer.Companion.asNMS
import com.rabbitown.yalib.module.nms.base.enum.EnumHand
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta

/**
 * @author Yoooooory
 */
@Path("NMSTest")
@Access(["yalib.admin"])
class CommandNMSTest : SimpleCommandRemote("yalib", YaLib.instance) {

    @Action("send")
    @Access(sender = [CommandSenderType.PLAYER])
    fun send(sender: Player) {
        sender.asNMS().sendMessage(PlainTextElement("test!!!")
            .apply { hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, JSONText(PlainTextElement("test!!!"))) })
    }

    @Action("openBook")
    @Access(sender = [CommandSenderType.PLAYER])
    fun openBook(sender: Player) {
        sender.asNMS().openBook(EnumHand.MAIN_HAND)
    }

    @Action("openBook0")
    @Access(sender = [CommandSenderType.PLAYER])
    fun openBook0(sender: Player) {
        val book = ItemStack(Material.WRITTEN_BOOK)
        book.itemMeta = (book.itemMeta as BookMeta).apply {
            author = "YaLib Developer"
            title = "Developing Diary"
            pages = listOf("YaLib v1.0.0-alpha.22\n\nWe have reached space.")
        }
        sender.asNMS().openBook(book)
    }

}