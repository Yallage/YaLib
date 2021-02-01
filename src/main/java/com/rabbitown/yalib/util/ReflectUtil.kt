package com.rabbitown.yalib.util

/**
 * @author Yoooooory
 */
class ReflectUtil private constructor() {
    companion object {

        @JvmStatic
        fun Class<*>.getMethodByName(name: String) =
            this.methods.firstOrNull { it.name == name } ?: throw NoSuchFieldException()

    }
}