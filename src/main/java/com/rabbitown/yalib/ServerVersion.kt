package com.rabbitown.yalib

import com.rabbitown.yalib.util.SystemUtil

/**
 * @author Yoooooory
 */
class ServerVersion private constructor() {
    companion object {
        val CURRENT = SystemUtil.dataVersion

        const val V1_16_4 = 2584
        const val V1_16_5 = 2586
    }
}