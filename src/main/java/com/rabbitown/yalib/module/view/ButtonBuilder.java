package com.rabbitown.yalib.module.view;

import com.rabbitown.yalib.common.model.action.IButtonAction;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ButtonBuilder {
    private ItemStack itemStack = new ItemStack(Material.AIR);
    private Map<ClickType, List<IButtonAction>> action = new HashMap<>();

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ButtonBuilder addAction(ClickType clickType, IButtonAction buttonAction){
        if(this.action.get(clickType).isEmpty()){
            List<IButtonAction> targetList = new ArrayList<>();
            targetList.add(buttonAction);
            this.action.put(clickType,targetList);
        }else {
            List<IButtonAction> targetList = this.action.get(clickType);
            targetList.add(buttonAction);
            this.action.put(clickType,targetList);
        }
        return this;
    }

    public void setAction(Map<ClickType, List<IButtonAction>> action) {
        this.action = action;
    }

    public Map<ClickType, List<IButtonAction>> getAction() {
        return action;

    }
}
