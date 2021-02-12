package com.rabbitown.yalib.module.locale

import com.rabbitown.yalib.YaLib
import com.rabbitown.yalib.util.FileUtil
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.permissions.ServerOperator
import java.io.File

/**
 * @author Yoooooory
 */
object LocaleManager {

    private val pluginToLocale = mutableMapOf<I18NPlugin, ILocale>()
    private val dataFile = YaLib.instance.dataFolder.resolve(File("data","languageData.yml"))

    fun getLocale(plugin: I18NPlugin) = pluginToLocale[plugin]

    fun load() {
        dataFile.createNewFile()
    }

    internal fun register(plugin: I18NPlugin) {
        pluginToLocale[plugin] = plugin.getNewLocale()
    }

    fun getLanguage(sender: ServerOperator): String {
        // Get user setting as default if exists, otherwise the global default.
        return when (sender) {
            is OfflinePlayer -> {
                val data = YamlConfiguration.loadConfiguration(dataFile)
                val lang = sender.name?.let { data.getString(it) }
                if (lang != null) data[lang] as String else YLocale.getDefaultLanguage()
            }
            is ConsoleCommandSender -> YLocale.getConsoleLanguage()
            else -> YLocale.getDefaultLanguage()
        }
    }

    fun setLanguage(target: CommandSender, lang: String) {
        TODO()
    }

}