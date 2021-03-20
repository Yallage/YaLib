package com.rabbitown.yalib.module.chat

import com.rabbitown.yalib.module.chat.text.JSONText
import org.bukkit.command.CommandSender

/**
 * Represents a message.
 *
 * @author Milkory
 */
interface IMessage {

    var message: JSONText

    fun send()
    fun sendTo(target: CommandSender)

}