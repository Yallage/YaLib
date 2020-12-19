package com.rabbitown.yalib.command

import com.rabbitown.yalib.command.annotation.Access

/**
 * @author Yoooooory
 */
interface Limitable {
    val access: Access
    val path: String
    val priority: Int
}