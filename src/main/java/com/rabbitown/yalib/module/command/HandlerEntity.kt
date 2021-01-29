package com.rabbitown.yalib.module.command

import com.rabbitown.yalib.module.command.annotation.Access

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