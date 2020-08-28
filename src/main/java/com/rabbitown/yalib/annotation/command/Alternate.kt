package com.rabbitown.yalib.annotation.command

/**
 * @author Yoooooory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class Alternate(val priority: Int = 0)