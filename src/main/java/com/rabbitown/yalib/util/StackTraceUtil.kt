package com.rabbitown.yalib.util

/**
 * @author Yoooooory
 */
class StackTraceUtil {
    companion object {

        fun getInvoker(exclude: Array<String> = emptyArray()): String {
            val elements = Thread.currentThread().stackTrace
            for (i in 3 until elements.size) {
                val name = elements[i].className
                if (exclude.all { name != it } && name != this::class.java.name) return name
            }
            error("I don't know why the error was thrown but it has been thrown.")
        }

    }
}