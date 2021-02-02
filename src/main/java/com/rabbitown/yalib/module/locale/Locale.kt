package com.rabbitown.yalib.module.locale

import com.rabbitown.yalib.module.locale.YLocale.Companion.getLocaleLanguage
import com.rabbitown.yalib.util.ExtendFunction.Companion.arg
import com.rabbitown.yalib.util.FileUtil
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.permissions.ServerOperator
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * @author Yoooooory
 */
open class Locale(
    val owner: JavaPlugin,

    /** The default language of the plugin.
     * Will usually be used when the global default language is not exist.
     *
     * Priority: User setting > Global default > Plugin default */
    val defaultLanguage: String = "en_US",

    /** The language folder of the plugin. */
    val languageFolder: File = File(owner.dataFolder, "/language"),

    /** The language folder inside the plugin. */
    val insideLanguageFolder: String = "language/"
) {

    val langMap = mutableMapOf<String, YamlConfiguration>()

    init {
        save()
        load()
    }

    // Constructors for Java.
    constructor(owner: JavaPlugin) : this(owner, "en_US")
    constructor(owner: JavaPlugin, defaultLanguage: String) : this(
        owner,
        defaultLanguage,
        File(owner.dataFolder, "/language")
    )

    open fun load() {
        langMap.clear()
        languageFolder.listFiles()?.filter { "yml" == it.extension }?.forEach {
            val yaml = YamlConfiguration.loadConfiguration(it)
            yaml.addDefaults(
                YamlConfiguration.loadConfiguration(FileUtil.getResource(owner, insideLanguageFolder + it.name))
            )
            langMap[it.nameWithoutExtension] = yaml
        }
    }

    open fun save() {
        FileUtil.getAllResource(owner, insideLanguageFolder).forEach { FileUtil.saveResource(owner, it) }
    }

    open fun send(target: CommandSender, key: String, vararg args: String) =
        target.sendMessage(getMessage(target.getLocaleLanguage(), key, *args)!!)

    fun sendToConsole(key: String, vararg args: String) = send(Bukkit.getConsoleSender(), key, *args)

    fun broadcast(key: String, vararg args: String) {
        sendToConsole(key, *args)
        Bukkit.getOnlinePlayers().forEach { send(it, key, *args) }
    }

    fun getMessage(language: String, key: String, vararg args: String) = getLanguageMap(language)?.get(key)?.arg(*args)

    fun getLanguageMap(language: String) = if (language in langMap) LanguageMap(langMap[language]!!) else null
    fun getLanguageMap(target: ServerOperator) = getLanguageMap(target.getLocaleLanguage())

    class LanguageMap(private val yaml: YamlConfiguration) {
        operator fun get(key: String) = yaml[key]?.toString()
        fun getList(key: String) = yaml.getList(key)?.map { it.toString() }
    }

}