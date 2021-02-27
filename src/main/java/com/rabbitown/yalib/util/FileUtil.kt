package com.rabbitown.yalib.util

import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.jar.JarFile

/**
 * A util to operator files in a plugin.
 *
 * @author Milkory
 */
class FileUtil private constructor() {
    companion object {

        @JvmStatic
        fun getAllResource(plugin: Plugin) = getAllResource(plugin, "")

        /** Get all resource path of a plugin. */
        @JvmStatic
        fun getAllResource(plugin: Plugin, dir: String): List<String> {
            val list = mutableListOf<String>()
            val prefix = if (dir.isEmpty() || dir.endsWith("/")) dir else "$dir/"
            JarFile(File(plugin.javaClass.protectionDomain.codeSource.location.toURI())).entries().iterator().forEach {
                if (it.name.startsWith(prefix) && it.name != prefix) list += it.name
            }
            return list
        }

        @JvmStatic
        fun getResource(plugin: Plugin, path: String) = InputStreamReader(plugin.getResource(path)!!, StandardCharsets.UTF_8)

        @JvmStatic
        fun saveResource(plugin: Plugin, path: String, replace: Boolean = false) {
            if (replace || !File(plugin.dataFolder, path).exists()) plugin.saveResource(path, replace)
        }

    }
}