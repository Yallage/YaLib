package com.rabbitown.yalib.util.utils

/**
 * @author Yoooooory
 */
class StackTraceUtil private constructor() {
    companion object {

        fun getInvoker(exclude: Array<Regex> = emptyArray()): String {
            val elements = Thread.currentThread().stackTrace
            for (i in 2 until elements.size) {
                val name = elements[i].className
                if (exclude.none { name.matches(it) } && name != this::class.java.name) return name
            }
            error("I don't know why the error was thrown but it has been thrown.")
        }

    }
}