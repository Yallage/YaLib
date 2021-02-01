package com.rabbitown.yalib.module.nms

import org.bukkit.Bukkit

/**
 * @author Yoooooory
 */
object NMSManager {

    // TODO: sometimes we can also imagine running without CraftBukkit :P

    val nmsPackage = "net.minecraft.server.${NMSVersion.CURRENT}"
    val craftPackage = Bukkit.getServer()::class.java.`package`.name
    private val implPackage = "com.rabbitown.yalib.nms.impl"

    fun getNMSClass(path: String): Class<*> = Class.forName("$nmsPackage.$path")
    fun getCraftClass(path: String): Class<*> = Class.forName("$craftPackage.$path")

    fun getImplClass(path: String): Class<*> = Class.forName("$implPackage.$path")
    // FIXME: ↑ might occur an error [ImplClass not found] (should load impl classes first)

}