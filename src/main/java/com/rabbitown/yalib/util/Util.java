package com.rabbitown.yalib.util;

/**
 * Some random functions.
 *
 * @author APJifengc
 */
public class Util {
    /**
     * Get slot ID in a inventory.
     *
     * @param x X in the inventory. (Start from 1)
     * @param y Y in the inventory. (Start from 1)
     * @return The slot ID in the inventory.
     */
    public static int getSlot(int x, int y) {
        return y*9+x-10;
    }
}
