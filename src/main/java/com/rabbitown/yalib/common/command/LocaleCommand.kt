package com.rabbitown.yalib.common.command

import com.rabbitown.yalib.YaLib
import com.rabbitown.yalib.module.command.SimpleCommandRemote
import com.rabbitown.yalib.module.command.annotation.Action
import com.rabbitown.yalib.module.command.annotation.Path

/**
 * @author Yoooooory
 */
@Path("locale")
class LocaleCommand : SimpleCommandRemote("yalib", YaLib.instance) {

    @Action("setLanguage {lang}")
    fun setLanguage(lang: String): String {
        return "没做."
    }

}