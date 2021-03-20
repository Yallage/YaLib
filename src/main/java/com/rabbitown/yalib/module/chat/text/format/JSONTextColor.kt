package com.rabbitown.yalib.module.chat.text.format

import org.bukkit.Color

/**
 * @author Milkory
 */
class JSONTextColor private constructor(private val code: JSONTextColors?, private val color: Color?) {

    constructor(code: JSONTextColors) : this(code, null)
    constructor(color: Color) : this(null, color)

    fun isHighVersion() = color != null

    override fun toString() = when {
        code != null -> code.toString()
        color != null -> "#${color.asRGB()}"
        else -> error("Both code and color are null.")
    }

}

enum class JSONTextColors {
    BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE
}
