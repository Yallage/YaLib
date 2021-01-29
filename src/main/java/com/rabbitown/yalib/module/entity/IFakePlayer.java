package com.rabbitown.yalib.module.entity;

import org.bukkit.Location;
import org.bukkit.World;

public interface IFakePlayer {
    /**
     * 创建一个假人
     *
     * @param displayName 显示的玩家名
     * @param world       世界
     * @param location    坐标
     */
    void createFakePlayer(String displayName, World world, Location location);
}
