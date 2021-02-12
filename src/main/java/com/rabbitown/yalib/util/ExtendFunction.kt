package com.rabbitown.yalib.util

/**
 * @author Yoooooory
 */
class ExtendFunction {
    companion object {

        /** Replace `{*}` (`*` is a number) in the string with parameters in order starting from 0. */
        fun String.arg(vararg obj: Any): String {
            var src = this
            for (i in obj.indices) src = src.replace("{$i}", obj[i].toString())
            return src
        }

        fun String.escape() = replace("\\", "\\\\").replace("\"", "\\\"")

    }
}