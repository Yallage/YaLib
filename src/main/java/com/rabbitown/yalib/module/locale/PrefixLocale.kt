package com.rabbitown.yalib.module.locale

import com.rabbitown.yalib.module.locale.YLocale.Companion.getLocaleLanguage
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class PrefixLocale(
    owner: JavaPlugin,
    val prefixKey: String = "prefix",
    defaultLanguage: String = "en_US",
    languageFolder: File = File(owner.dataFolder, "/language"),
    insideLanguageFolder: String = "language/"
) : Locale(owner, defaultLanguage, languageFolder, insideLanguageFolder) {

    // Constructors for Java.
    constructor(owner: JavaPlugin) : this(owner, "prefix")
    constructor(owner: JavaPlugin, prefixKey: String) : this(owner, prefixKey, "en_US")
    constructor(owner: JavaPlugin, prefixKey: String, defaultLanguage: String) :
            this(owner, prefixKey, defaultLanguage, File(owner.dataFolder, "/language"))

    override fun send(target: CommandSender, key: String, vararg args: String) =
        target.sendMessage(getMessage(target.getLocaleLanguage(), prefixKey) + getMessage(target.getLocaleLanguage(), key, *args)!!)

}