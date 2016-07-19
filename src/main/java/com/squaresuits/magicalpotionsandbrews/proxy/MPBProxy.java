package com.squaresuits.magicalpotionsandbrews.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Jeffrey on 7/18/2016.
 */
public interface MPBProxy {
    public EntityPlayer getPlayer();

    public World getWorld();
}
