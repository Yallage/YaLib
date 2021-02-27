package com.rabbitown.yalib.module.chat.text.impl

import com.rabbitown.yalib.module.chat.text.JSONTextElement

/**
 * @author Milkory
 */
class ScoreElement(val name: String, val objective: String, val value: String? = null) : JSONTextElement() {
    // Constructor for Java.
    constructor(name: String, objective: String) : this(name, objective, null)
}