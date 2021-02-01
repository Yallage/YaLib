package com.rabbitown.yalib.util

import com.google.gson.Gson
import java.util.*

/**
 * @author Yoooooory
 */
class SystemUtil private constructor() {
    companion object {

        @JvmStatic val nullUUID = UUID(0L, 0L)
        @JvmStatic val gson = Gson()

    }
}