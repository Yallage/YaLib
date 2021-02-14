package com.rabbitown.yalib.module.locale

import com.rabbitown.yalib.YaLib
import com.rabbitown.yalib.YaLibCentral
import com.rabbitown.yalib.util.StackTraceUtil
import org.bukkit.command.CommandSender
import org.bukkit.permissions.ServerOperator
import org.bukkit.plugin.Plugin

/**
 * I18N forwarding hub.
 *
 * @author Yoooooory
 */
class YLocale {

    @Suppress("unused")
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

        // region Sending functions
        @JvmStatic
        fun send(target: CommandSender, key: String, vararg args: String) =
            checkInvoker().locale.send(target, key, *args)

        @JvmStatic
        fun sendToConsole(key: String, vararg args: String) = checkInvoker().locale.sendToConsole(key, *args)

        @JvmStatic
        fun broadcast(key: String, vararg args: String) = checkInvoker().locale.broadcast(key, *args)
        // endregion

        // region Message getting functions
        @JvmStatic
        fun getMessage(language: String, key: String, vararg args: String): String? {
            return checkInvoker().locale.getMessage(language, key, *args)
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
        // endregion

        // region Language map getting functions
        @JvmStatic
        fun getLanguageMap(language: String) = checkInvoker().locale.getLanguageMap(language)

        @JvmStatic
        fun getLanguageMap(sender: ServerOperator) = checkInvoker().locale.getLanguageMap(sender)
        // endregion

        // region Language settings getting functions
        @JvmStatic
        fun getDefaultLanguage() = YaLib.instance.config["language.default"] as String

        @JvmStatic
        fun getConsoleLanguage() = YaLib.instance.config["language.console"] as String
        // endregion

        // region Extend functions
        fun CommandSender.sendLocale(key: String, vararg args: String) = send(this, key, *args)
        fun ServerOperator.getLocaleMessage(key: String) = getMessage(this, key)
        fun ServerOperator.getLocaleLanguage() = getLanguage(this)
        fun ServerOperator.getLocaleLanguageMap() = getLanguageMap(this)
        // endregion

    }

}