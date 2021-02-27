package com.rabbitown.yalib.module.locale

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.permissions.ServerOperator

/**
 * Represents a locale hub, which can manage the locale of its [I18NPlugin] owner.
 *
 * @author Milkory
 */
interface ILocale {

    /** Release language files from the plugin. */
    fun save()

    /** Load language files from the released files. */
    fun load()

    /** Send a locale message to a specified target. */
    fun send(target: CommandSender, key: String, vararg args: String)

    fun sendToConsole(key: String, vararg args: String) = send(Bukkit.getConsoleSender(), key, *args)
    fun broadcast(key: String, vararg args: String) {
        sendToConsole(key, *args)
        Bukkit.getOnlinePlayers().forEach { send(it, key, *args) }
    }

    /**
     * Get a localed message of a specified language.
     *
     * @return The localed message, or null if the message not found.
     */
    fun getMessage(language: String, key: String, vararg args: String): String?

    fun getLanguageMap(language: String): LanguageMap?
    fun getLanguageMap(target: ServerOperator): LanguageMap?

    /** A language map is a [YamlConfiguration] which will convert all object to [String] when getting. */
    class LanguageMap internal constructor(private val yaml: YamlConfiguration) {
        operator fun get(key: String) = yaml[key]?.toString()
        fun getList(key: String) = yaml.getList(key)?.map { it.toString() }
    }

}