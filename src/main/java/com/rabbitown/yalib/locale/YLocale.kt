package com.rabbitown.yalib.locale

import com.rabbitown.yalib.YaLib
import com.rabbitown.yalib.YaLibCentral
import com.rabbitown.yalib.util.FileUtil
import com.rabbitown.yalib.util.StackTraceUtil
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.permissions.ServerOperator

/**
 * I18N forwarding hub.
 *
 * @author Yoooooory
 */
class YLocale {

    companion object {

        private fun getInvoker() =
            YaLibCentral.getPlugin(StackTraceUtil.getInvoker(arrayOf("com.rabbitown.yalib.i18n")))

        private fun checkInvoker() =
            getInvoker().apply { if (this !is I18NPlugin) error("A non-I18N plugin tried to use YLocale.") }!! as I18NPlugin

        @JvmStatic
        fun getLanguage(sender: ServerOperator): String {
            // Get user setting as default if exists, otherwise the global default.
            val default = when (sender) {
                is OfflinePlayer -> {
                    val data = YamlConfiguration.loadConfiguration(
                        FileUtil.getResource(YaLib.instance, "data/languageData.yml")
                    )
                    val name = (sender as CommandSender).name
                    if (data[name] != null) data[name] as String else getDefaultLanguage()
                }
                is ConsoleCommandSender -> getConsoleLanguage()
                else -> getDefaultLanguage()
            }
            // Check if the invoker plugin contains the default language.
            val plugin = getInvoker()
            return if (plugin is I18NPlugin && default !in plugin.locale.langMap) plugin.locale.defaultLanguage else default
        }

        // SENDING //

        @JvmStatic
        fun send(target: CommandSender, key: String, vararg args: String) =
            checkInvoker().locale.send(target, key, *args)

        @JvmStatic
        fun sendToConsole(key: String, vararg args: String) = checkInvoker().locale.sendToConsole(key, *args)

        @JvmStatic
        fun broadcast(key: String, vararg args: String) = checkInvoker().locale.broadcast(key, *args)

        // GET MESSAGE //

        @JvmStatic
        fun getMessage(language: String, key: String, vararg args: String) =
            checkInvoker().locale.getMessage(language, key, *args)

        @JvmStatic
        fun getMessage(target: ServerOperator, key: String, vararg args: String) =
            checkInvoker().locale.getMessage(getLanguage(target), key, *args)

        @JvmStatic
        fun getConsoleMessage(key: String, vararg args: String) =
            checkInvoker().locale.getMessage(getConsoleLanguage(), key, *args)

        @JvmStatic
        fun getDefaultMessage(key: String, vararg args: String) =
            checkInvoker().locale.getMessage(getDefaultLanguage(), key, *args)

        // GET LANGUAGE MAP

        @JvmStatic
        fun getLanguageMap(sender: ServerOperator) = checkInvoker().locale.getLanguageMap(sender)

        @JvmStatic
        fun getLanguageMap(language: String) = checkInvoker().locale.getLanguageMap(language)

        // GET LANGUAGE SETTING

        @JvmStatic
        fun getDefaultLanguage() = YaLib.instance.config["language.default"] as String

        @JvmStatic
        fun getConsoleLanguage() = YaLib.instance.config["language.console"] as String

        fun CommandSender.sendLocale(key: String, vararg args: String) = send(this, key, *args)
        fun ServerOperator.getLocaleMessage(key: String) = getMessage(this, key)
        fun ServerOperator.getLocaleLanguage() = getLanguage(this)
        fun ServerOperator.getLocaleLanguageMap() = getLanguageMap(this)

    }

}