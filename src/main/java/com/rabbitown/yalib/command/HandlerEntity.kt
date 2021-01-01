package com.rabbitown.yalib.command

import com.rabbitown.yalib.command.annotation.Access

/**
 * @author Yoooooory
 */
interface HandlerEntity {
    val access: Access
    val priority: Int
}

interface MainHandler : HandlerEntity {
    val path: String
    val ignoreCase: Boolean
}