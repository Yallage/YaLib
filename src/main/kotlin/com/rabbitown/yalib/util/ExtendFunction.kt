package com.rabbitown.yalib.util

/**
 * @author Yoooooory
 */
object ExtendFunction {

    fun String.arg(vararg obj: Any): String {
        var src = this
        for (i in obj.indices) src = src.replace("%$i\$s", obj[i].toString())
        return src
    }

}