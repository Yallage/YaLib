package com.rabbitown.yalib.util

import com.google.gson.Gson
import java.util.*
import java.util.function.Consumer

/**
 * @author Yoooooory
 */
class SystemUtil private constructor() {
    companion object {

        @JvmStatic val nullUUID = UUID(0L, 0L)
        @JvmStatic val gson = Gson()

        @JvmStatic
        fun measureNanoTime(method: Runnable): Long {
            val start = System.nanoTime()
            method.run()
            return System.nanoTime() - start
        }

    }
}