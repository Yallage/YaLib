package com.rabbitown.yalib.module.chat.text.format

import com.google.gson.JsonObject
import com.rabbitown.yalib.module.nms.NMSVersion
import org.bukkit.NamespacedKey

/**
 * Represents a format of Raw JSON Text elements.
 *
 * @author Yoooooory
 */
class JSONTextFormat(
    var color: JSONTextColor? = null,
    var bold: Boolean = false,
    var italic: Boolean = false,
    var underlined: Boolean = false,
    var strikethrough: Boolean = false,
    var obfuscated: Boolean = false,
    var font: NamespacedKey? = null
) {

    constructor() : this(null) // Constructor for Java.

    fun toJsonObject() = JsonObject().apply {
        // TODO: after NMS module finished there should be a version test of color ;P
        if (color != null)
            if (color!!.isHighVersion() && NMSVersion.CURRENT.isAfter(NMSVersion.V1_16_R1))
                error("Custom color doesn't support the version under 1.16")
            else addProperty("color", color.toString())
        addBoolean("bold", bold, this)
        addBoolean("italic", italic, this)
        addBoolean("underlined", underlined, this)
        addBoolean("strikethrough", strikethrough, this)
        addBoolean("obfuscated", obfuscated, this)
        if (font != null) addProperty("font", font.toString())
    }

    private fun addBoolean(key: String, bool: Boolean, json: JsonObject) = json.apply { if (bool) addProperty(key, true) }

}