package com.rabbitown.yalib.util

import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * ### Designed for Kotlin.
 * A util class to make the reflection codes more concise.
 *
 * @author Milkory
 */
class ReflectUtil private constructor() {
    companion object {

        @JvmStatic
        fun Class<*>.getMethodByName(name: String) = this.methods.firstOrNull { it.name == name }!!

        @JvmStatic
        fun Class<*>.getConstructorHasParams() = this.constructors.first { it.parameterCount > 0 }!!

        fun Field.access() = apply { isAccessible = true }
        fun Method.access() = apply { isAccessible = true }
        fun Constructor<*>.access() = apply { isAccessible = true }

    }
}