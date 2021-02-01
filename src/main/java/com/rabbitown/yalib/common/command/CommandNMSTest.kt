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
import org.bukkit.entity.Player

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

}