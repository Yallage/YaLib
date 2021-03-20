package com.rabbitown.yalib

import com.rabbitown.yalib.util.SystemUtil

/**
 * A util class to check the running server version.
 *
 * @author Milkory
 */
class ServerVersion private constructor() {
    companion object {
        /** The current running server version. */
        val CURRENT = SystemUtil.dataVersion

        const val V1_16_4 = 2584
        const val V1_16_5 = 2586
    }
}