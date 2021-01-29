package com.rabbitown.yalib.module.command.annotation

import java.lang.reflect.Method

/**
 * @author Yoooooory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class Parameter(vararg val params: String) {
    companion object {
        fun get(method: Method) = method.getDeclaredAnnotation(Parameter::class.java)!!
    }
}