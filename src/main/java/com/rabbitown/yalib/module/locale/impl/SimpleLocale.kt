package com.rabbitown.yalib.module.locale.impl

import com.rabbitown.yalib.module.locale.I18NPlugin
import com.rabbitown.yalib.module.locale.ILocale
import com.rabbitown.yalib.module.locale.YLocale.Companion.getLocaleLanguage
import com.rabbitown.yalib.util.ExtendFunction.Companion.arg
import com.rabbitown.yalib.util.FileUtil
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.permissions.ServerOperator
import java.io.File

/**
 * An implement of [ILocale].
 *
 * @author Yoooooory
 */
open class SimpleLocale(
    val owner: I18NPlugin,
    val fallbackLanguages: Array<String> = arrayOf("en_US", "zh_CN"),
    val languageFolder: File = File(owner.dataFolder, "/language"),
    val insideFolder: String = "language/"
) : ILocale {

    private val langMap = mutableMapOf<String, YamlConfiguration>()

    init {
        save()
        load()
    }

    final override fun load() {
        langMap.clear()
        languageFolder.listFiles()?.filter { "yml" == it.extension }?.forEach {
            val yaml = YamlConfiguration.loadConfiguration(it)
            yaml.addDefaults(YamlConfiguration.loadConfiguration(FileUtil.getResource(owner, insideFolder + it.name)))
            langMap[it.nameWithoutExtension] = yaml
        }
        if (langMap.isEmpty()) error("No language file was found in inside folder.")
    }

    final override fun save() {
        FileUtil.getAllResource(owner, insideFolder).forEach { FileUtil.saveResource(owner, it) }
    }

    override fun send(target: CommandSender, key: String, vararg args: String) =
        target.sendMessage(getMessage(target.getLocaleLanguage(), key, *args)!!)

    override fun getMessage(language: String, key: String, vararg args: String): String? {
        return getLanguageMap(language)?.get(key)?.arg(*args) ?: let {
            fallbackLanguages.forEach { lang ->
                val msg = getLanguageMap(lang)?.get(key)?.arg(*args)
                if (msg != null) return msg
            }
            null
        }
    }

    override fun getLanguageMap(language: String) = langMap[language]?.let { ILocale.LanguageMap(it) }
    override fun getLanguageMap(target: ServerOperator) = getLanguageMap(target.getLocaleLanguage())

    open class Builder(val owner: I18NPlugin) {
        var fallbackLanguage = mutableListOf("en_US", "zh_CN")
        var languageFolder: File = File(owner.dataFolder, "/language")
        var insideFolder: String = "language/"

        fun addFallbacks(vararg lang: String) = apply { fallbackLanguage.addAll(lang) }
        fun fallback(vararg lang: String) = apply { fallbackLanguage = lang.toMutableList() }
        fun languageFolder(folder: File) = apply { languageFolder = folder }
        fun insideFolder(folder: String) = apply { insideFolder = folder }

        open fun build() = SimpleLocale(owner, fallbackLanguage.toTypedArray(), languageFolder, insideFolder)
    }

}