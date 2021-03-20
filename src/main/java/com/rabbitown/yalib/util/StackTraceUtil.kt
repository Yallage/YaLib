package com.rabbitown.yalib.util

/**
 * A util class to operator the stack trace.
 *
 * @author Milkory
 */
class StackTraceUtil private constructor() {
    companion object {

        /**
         * Get the method invoker.
         *
         * @param exclude What to exclude, can be an empty array.
         * @return The invoker class' name from [StackTraceElement.getClassName].
         */
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