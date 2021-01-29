package com.rabbitown.yalib.module.nms

import org.bukkit.Bukkit

/**
 * @author Yoooooory
 */
object NMSManager {

    val nmsPackage = Bukkit.getServer()::class.java.`package`.name
    private val implPackage = "com.rabbitown.yalib.nms.impl"

    fun getNMSClass(path: String): Class<*> = Class.forName("$nmsPackage.$path")

    fun getImplClass(path: String): Class<*> = Class.forName("$implPackage.$path")
    // FIXME: ↑ might occur an error [ImplClass not found] (should load impl classes first)

}