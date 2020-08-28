package com.rabbitown.yalib.annotation.command

/**
 * @author Yoooooory
 */
@Repeatable
@Target(AnnotationTarget.CLASS)
annotation class ExtendHandler(
    val name: String,
    val description: String = "No description provided.",
    val aliases: Array<String> = [],
    val permission: String = "",
    val permissionMessage: String = "§cYou don't have permission to use this command.",
    val usage: String = "No usage provided."
)