package com.rabbitown.yalib.util

/**
 * @author Yoooooory
 */
class StackTraceUtil private constructor() {
    companion object {

        @JvmStatic
        fun getInvoker(exclude: Array<Regex> = emptyArray()): String {
            val elements = Thread.currentThread().stackTrace
            for (i in 2 until elements.size) {
                val name = elements[i].className
                if (exclude.none { name.matches(it) } && name != this::class.java.name) return name
            }
            throw NoSuchElementException()
        }

    }
}