package com.rabbitown.yalib.util

import com.google.gson.Gson
import org.bukkit.Bukkit
import java.util.*
import java.util.function.Consumer

/**
 * Misc utils.
 *
 * @author Milkory
 */
class SystemUtil private constructor() {
    companion object {

        @JvmStatic val nullUUID = UUID(0L, 0L)
        @JvmStatic val gson = Gson()

        @Suppress("DEPRECATION")
        @JvmStatic val dataVersion = Bukkit.getUnsafe().dataVersion

        @JvmStatic
        fun measureNanoTime(method: Runnable): Long {
            val start = System.nanoTime()
            method.run()
            return System.nanoTime() - start
        }

    }
}