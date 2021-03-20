package com.rabbitown.yalib.module.command.annotation

import java.lang.reflect.Method

/**
 * ### Only in command module.
 * An annotation to declare the parameters of a handler method.
 *
 * @author Milkory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class Parameter(vararg val params: String) {
    companion object {
        fun get(method: Method) = method.getDeclaredAnnotation(Parameter::class.java)!!
    }
}