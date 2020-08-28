package com.rabbitown.yalib.annotation.command

/**
 * @author Yoooooory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class After(val priority: Int = 0)