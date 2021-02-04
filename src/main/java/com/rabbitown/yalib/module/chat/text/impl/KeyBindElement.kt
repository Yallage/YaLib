package com.rabbitown.yalib.module.chat.text.impl

import com.google.gson.annotations.SerializedName
import com.rabbitown.yalib.module.chat.text.JSONTextElement

/**
 * @author Yoooooory
 */
class KeyBindElement(val keybind: KeyBind) : JSONTextElement() {
    enum class KeyBind {
        @SerializedName("key.jump")
        JUMP,

        @SerializedName("key.sneak")
        SNEAK,

        @SerializedName("key.sprint")
        SPRINT,

        @SerializedName("key.left")
        LEFT,

        @SerializedName("key.right")
        RIGHT,

        @SerializedName("key.back")
        BACK,

        @SerializedName("key.forward")
        FORWARD,

        @SerializedName("key.attack")
        ATTACK,

        @SerializedName("key.pickItem")
        PICK_ITEM,

        @SerializedName("key.use")
        USE,

        @SerializedName("key.drop")
        DROP,

        @SerializedName("key.hotbar.1")
        HOTBAR_1,

        @SerializedName("key.hotbar.2")
        HOTBAR_2,

        @SerializedName("key.hotbar.3")
        HOTBAR_3,

        @SerializedName("key.hotbar.4")
        HOTBAR_4,

        @SerializedName("key.hotbar.5")
        HOTBAR_5,

        @SerializedName("key.hotbar.6")
        HOTBAR_6,

        @SerializedName("key.hotbar.7")
        HOTBAR_7,

        @SerializedName("key.hotbar.8")
        HOTBAR_8,

        @SerializedName("key.hotbar.9")
        HOTBAR_9,

        @SerializedName("key.inventory")
        INVENTORY,

        @SerializedName("key.swapHands")
        SWAP_HANDS,

        @SerializedName("key.loadToolbarActivator")
        LOAD_TOOLBAR_ACTIVATOR,

        @SerializedName("key.saveToolbarActivator")
        SAVE_TOOLBAR_ACTIVATOR,

        @SerializedName("key.playerlist")
        PLAYERLIST,

        @SerializedName("key.chat")
        CHAT,

        @SerializedName("key.command")
        COMMAND,

        @SerializedName("key.advancements")
        ADVANCEMENTS,

        @SerializedName("key.spectatorOutlines")
        SPECTATOR_OUTLINES,

        @SerializedName("key.screenshot")
        SCREENSHOT,

        @SerializedName("key.smoothCamera")
        SMOOTH_CAMERA,

        @SerializedName("key.fullscreen")
        FULLSCREEN,

        @SerializedName("key.togglePerspective")
        TOGGLE_PERSPECTIVE;
    }
}