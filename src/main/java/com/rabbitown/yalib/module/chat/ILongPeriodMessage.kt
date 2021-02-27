package com.rabbitown.yalib.module.chat

import com.rabbitown.yalib.module.chat.text.JSONText
import com.rabbitown.yalib.module.chat.text.impl.PlainTextElement
import org.bukkit.command.CommandSender

/**
 * Represents a message which have long period and can be updated or unsent.
 *
 * @author Milkory
 */
interface ILongPeriodMessage : IMessage {

    val showing: Boolean

    fun tempSend(time: Int, delay: Int): Unit = throw UnsupportedOperationException()
    fun tempSendTo(sender: CommandSender, time: Int, delay: Int): Unit = throw UnsupportedOperationException()

    fun unsent(): Unit = throw UnsupportedOperationException()

    fun update(): Unit = throw UnsupportedOperationException()

    fun setMessage(text: String) {
        message = JSONText(PlainTextElement(text))
    }

}