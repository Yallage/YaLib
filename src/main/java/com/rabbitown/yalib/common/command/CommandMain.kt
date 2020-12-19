package com.rabbitown.yalib.common.command

import com.rabbitown.yalib.command.CommandRemote
import com.rabbitown.yalib.command.annotation.Action

/**
 * @author Yoooooory
 */
class CommandMain : CommandRemote("yalib") {

    @Action("help")
    fun help() = "test!"

}