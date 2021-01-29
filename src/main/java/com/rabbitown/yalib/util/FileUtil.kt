package com.rabbitown.yalib.util

import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.InputStreamReader
import java.util.jar.JarFile

/**
 * @author Yoooooory
 */
class FileUtil private constructor() {
    companion object {

        @JvmStatic
        fun getAllResource(plugin: JavaPlugin, dir: String): List<String> {
            val list = mutableListOf<String>()
            val prefix = if (dir.endsWith("/")) dir else "$dir/"
            JarFile(File(plugin.javaClass.protectionDomain.codeSource.location.toURI())).entries().iterator().forEach {
                if (it.name.startsWith(prefix) && it.name != prefix) list += it.name
            }
            return list
        }

        @JvmStatic
        fun getResource(plugin: JavaPlugin, path: String) = InputStreamReader(plugin.getResource(path)!!)

        @JvmStatic
        fun saveResource(plugin: JavaPlugin, path: String, replace: Boolean = false) {
            if (replace || !File(plugin.dataFolder, path).exists()) plugin.saveResource(path, replace)
        }

    }
}