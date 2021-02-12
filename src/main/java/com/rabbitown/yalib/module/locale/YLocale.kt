package com.rabbitown.yalib.module.locale

import com.rabbitown.yalib.YaLib
import com.rabbitown.yalib.YaLibCentral
import com.rabbitown.yalib.util.FileUtil
import com.rabbitown.yalib.util.StackTraceUtil
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.permissions.ServerOperator
import org.bukkit.plugin.Plugin

/**
 * I18N forwarding hub.
 *
 * @author Yoooooory
 */
class YLocale {

    companion object {

        private val excludeRegex = Regex("${this::class.java.`package`.name}.*")

        private fun getInvoker(): Plugin {
            val invoker = StackTraceUtil.getInvoker(arrayOf(excludeRegex))
            return YaLibCentral.getPlugin(invoker) ?: error("$invoker hasn't registered in YaLibCentral.")
        }

        private fun checkInvoker() =
            getInvoker().apply { check(this is I18NPlugin) { "A non-I18N plugin tried to use YLocale." } } as I18NPlugin

        @JvmStatic
        fun getLanguage(sender: ServerOperator) = LocaleManager.getLanguage(sender)

        // SENDING //

        @JvmStatic
        fun send(target: CommandSender, key: String, vararg args: String) =
            checkInvoker().getLocale().send(target, key, *args)

        @JvmStatic
        fun sendToConsole(key: String, vararg args: String) = checkInvoker().getLocale().sendToConsole(key, *args)

        @JvmStatic
        fun broadcast(key: String, vararg args: String) = checkInvoker().getLocale().broadcast(key, *args)

        // GET MESSAGE //

        @JvmStatic
        fun getMessage(language: String, key: String, vararg args: String): String? {
            return checkInvoker().getLocale().getMessage(language, key, *args)
        }

        @JvmStatic
        fun getMessage(target: ServerOperator, key: String, vararg args: String): String? {
            return getMessage(getLanguage(target), key, *args)
        }

        @JvmStatic
        fun getConsoleMessage(key: String, vararg args: String): String? {
            return getMessage(getConsoleLanguage(), key, *args)
        }

        @JvmStatic
        fun getDefaultMessage(key: String, vararg args: String): String? {
            return getMessage(getDefaultLanguage(), key, *args)
        }

        // GET LANGUAGE MAP

        @JvmStatic
        fun getLanguageMap(language: String) = checkInvoker().getLocale().getLanguageMap(language)

        @JvmStatic
        fun getLanguageMap(sender: ServerOperator) = checkInvoker().getLocale().getLanguageMap(sender)

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