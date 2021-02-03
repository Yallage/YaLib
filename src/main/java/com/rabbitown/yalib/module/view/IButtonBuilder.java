package com.rabbitown.yalib.module.view;

import org.bukkit.inventory.ItemStack;

/**
 * 这个接口仅仅是为了兼容性和扩展性出现在这里
 * 唯一一个功能是为实现要求一个提供按钮
 * @author Hanbings
 */
public interface IButtonBuilder {
    /**
     * 获取按钮
     */
    public ItemStack getButton();
}
