package com.rabbitown.yalib.module.chat

import com.rabbitown.yalib.module.chat.text.JSONText
import org.bukkit.command.CommandSender

/**
 * @author Yoooooory
 */
interface IMessage {

    var message: JSONText

    fun send()
    fun sendTo(target: CommandSender)

}