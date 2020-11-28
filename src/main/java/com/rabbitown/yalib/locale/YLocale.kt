package com.rabbitown.yalib.locale

import com.rabbitown.yalib.YaLib
import com.rabbitown.yalib.YaLibCenter
import com.rabbitown.yalib.util.FileUtil
import com.rabbitown.yalib.util.StackTraceUtil
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.configuration.file.YamlConfiguration

/**
 * I18N forwarding hub.
 *
 * @author Yoooooory
 */
class YLocale {

    companion object {

        private fun getInvoker() = YaLibCenter.getPlugin(StackTraceUtil.getInvoker(arrayOf("com.rabbitown.yalib.i18n")))

        private fun checkInvoker() =
            getInvoker().apply { if (this !is I18NPlugin) error("Only I18N plugin can use YLocale.") }!! as I18NPlugin

        fun CommandSender.getLanguage(): String {
            // Get user setting as default if exists, otherwise the global default.
            val default = when (this) {
                is OfflinePlayer -> {
                    val data = YamlConfiguration.loadConfiguration(
                        FileUtil.getResource(YaLib.instance, "data/languageData.yml")
                    )
                    val name = (this as CommandSender).name
                    if (data[name] != null) data[name] as String else YaLib.instance.config["language.default"] as String
                }
                is ConsoleCommandSender -> YaLib.instance.config["language.console"] as String
                else -> YaLib.instance.config["language.default"] as String
            }
            // Check if the invoker plugin contains the default language.
            val plugin = getInvoker()
            return if (plugin is I18NPlugin && !plugin.locale.langMap.containsKey(default)) plugin.locale.defaultLanguage else default
            // Return the default.
        }

        @JvmStatic
        fun send(target: CommandSender, key: String, vararg args: String) =
            checkInvoker().locale.send(target, key, *args)

        @JvmStatic
        fun sendToConsole(key: String, vararg args: String) = checkInvoker().locale.sendToConsole(key, *args)

        @JvmStatic
        fun broadcast(key: String, vararg args: String) = checkInvoker().locale.broadcast(key, *args)

        fun CommandSender.sendLocale(key: String, vararg args: String) = send(this, key, *args)

    }

}