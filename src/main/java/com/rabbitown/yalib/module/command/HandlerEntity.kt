package com.rabbitown.yalib.module.command

import com.rabbitown.yalib.module.command.annotation.Access

/**
 * @author Milkory
 */
interface HandlerEntity {
    val access: Access
    val priority: Int
}

/**
 * @author Milkory
 */
interface MainHandler : HandlerEntity {
    val path: String
    val ignoreCase: Boolean
}