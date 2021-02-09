package com.rabbitown.yalib.util

import java.lang.reflect.Constructor
import java.lang.reflect.Method

/**
 * @author Yoooooory
 */
class ReflectUtil private constructor() {
    companion object {

        @JvmStatic
        fun Class<*>.getMethodByName(name: String) = this.methods.firstOrNull { it.name == name }!!

        @JvmStatic
        fun Class<*>.getConstructorHasParams() = this.constructors.first { it.parameterCount > 0 }!!

        fun Method.access() = apply { isAccessible = true }
        fun Constructor<*>.access() = apply { isAccessible = true }

    }
}