package com.rabbitown.yalib.module.view;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class WindowBuilder implements InventoryHolder {
    Inventory view;
    Map<Integer,ButtonBuilder> buttons;

    /**
     * 构造一个inventory
     * @param size 尺寸
     * @param title 标题
     */
     public WindowBuilder(int size,String title){
         this.view = Bukkit.createInventory(this,size,title);
     }

    /**
     * 直接传入inventory的构造
     * @param inventory Inventory
     */
     public WindowBuilder(Inventory inventory){
         this.view = inventory;
     }

    /**
     * 设置按钮
     * @param buttons 按钮
     */
    public void setButtons(Map<Integer,ButtonBuilder> buttons){
         this.buttons = buttons;
     }

    /**
     * 获取按钮
     * @return 按钮组
     */
    public Map<Integer, ButtonBuilder> getButtons() {
        return buttons;
    }

    /**
     * 立刻更新按钮
     */
    public void updateButton(){
         for (int count = 0;count<this.view.getSize();count++){
             this.view.setItem(count,this.buttons.get(count).getItemStack());
         }
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return view;
    }
}
