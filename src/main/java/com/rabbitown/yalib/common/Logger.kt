package com.rabbitown.yalib.common

import com.rabbitown.yalib.YaLib
import com.rabbitown.yalib.module.locale.YLocale
import org.bukkit.Bukkit

/**
 * The logger of [YaLib].
 *
 * @author Milkory
 */
object Logger {

    private val prefix = YLocale.getConsoleMessage("prefix")

    fun info(message: String) = Bukkit.getConsoleSender().sendMessage(prefix + message)
    fun severe(message: String?) = YaLib.instance.logger.severe(message)
    fun warning(message: String?) = YaLib.instance.logger.warning(message)

}