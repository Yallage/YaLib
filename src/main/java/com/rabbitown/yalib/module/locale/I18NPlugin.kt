package com.rabbitown.yalib.module.locale

import com.rabbitown.yalib.YaLibCentral
import com.rabbitown.yalib.module.locale.impl.SimpleLocale
import org.bukkit.plugin.Plugin

/**
 * Represents an i18n plugin.
 *
 * @author Yoooooory
 */
interface I18NPlugin : Plugin {

    /** When registering plugin to [YaLibCentral], this method will be invoked and create a new [ILocale] to be stored. */
    fun getNewLocale(): ILocale = SimpleLocale(this)

    /** Returns the locale instance of this plugin.
     *
     *  You are **not supported** to override this method. */
    fun getLocale(): ILocale = LocaleManager.getLocale(this)!!

}