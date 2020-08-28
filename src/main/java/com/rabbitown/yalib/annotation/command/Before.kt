package com.rabbitown.yalib.annotation.command

/**
 * @author Yoooooory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class Before(val priority: Int = 0)