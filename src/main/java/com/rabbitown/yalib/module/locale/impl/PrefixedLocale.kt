package com.rabbitown.yalib.module.locale.impl

import com.rabbitown.yalib.module.locale.I18NPlugin
import com.rabbitown.yalib.module.locale.YLocale.Companion.getLocaleLanguage
import org.bukkit.command.CommandSender
import java.io.File

class PrefixedLocale(
    owner: I18NPlugin,
    val prefixKey: String = "prefix",
    fallbackLanguages: Array<String> = arrayOf("en_US", "zh_CN"),
    languageFolder: File = File(owner.dataFolder, "/language"),
    insideLanguageFolder: String = "language/"
) : SimpleLocale(owner, fallbackLanguages, languageFolder, insideLanguageFolder) {

    override fun send(target: CommandSender, key: String, vararg args: String) =
        target.sendMessage(getMessage(target.getLocaleLanguage(), prefixKey) + getMessage(target.getLocaleLanguage(), key, *args)!!)

    class Builder(owner: I18NPlugin) : SimpleLocale.Builder(owner) {
        var prefixKey = "prefix"
        fun prefix(key: String) = apply { prefixKey = key }
        override fun build() = PrefixedLocale(owner, prefixKey, fallbackLanguage.toTypedArray(), languageFolder, insideFolder)
    }

    companion object {
        @JvmStatic
        fun newDefault(owner: I18NPlugin) = Builder(owner).build()
    }

}