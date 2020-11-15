package com.rabbitown.yalib.chat.impl;

import com.rabbitown.yalib.YaLib;
import com.rabbitown.yalib.chat.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FluidTitle implements Title {
    @Override
    public void title(Player player, String title) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin) this, () -> {
            Bukkit.getScheduler().runTask(YaLib.instance, () -> this.sendTitle(player,title));
        });
    }

    public void sendTitle(Player player,String title){
        String waitSend = null;
        List<String> list= Stream.iterate(0, n -> ++n).limit(title.length())
                .map(n -> "" + title.charAt(n))
                .collect(Collectors.toList());
        for(String str:list){
            System.out.println(str);
            waitSend = waitSend + str;
            player.sendTitle(waitSend,null,10,70,20);
        }
    }
}
