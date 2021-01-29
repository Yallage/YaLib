package com.rabbitown.yalib.util

/**
 * @author Yoooooory
 */
class ExtendFunction {
    companion object {

        fun String.arg(vararg obj: Any): String {
            var src = this
            for (i in obj.indices) src = src.replace("{$i}", obj[i].toString())
            return src
        }

    }
}