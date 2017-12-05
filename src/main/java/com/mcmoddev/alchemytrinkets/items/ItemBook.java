package com.mcmoddev.alchemytrinkets.items;

import java.util.List;

import com.mcmoddev.alchemytrinkets.MPBGlobal;
import com.mcmoddev.alchemytrinkets.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;


public class ItemBook extends Item {
    public int guiId = 0;
    public ItemBook(String name, int gui){
        setUnlocalizedName(name);
        this.guiId = gui;
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){;
        player.openGui(Main.instance, guiId + (hand == EnumHand.MAIN_HAND ? 0 : 1), world, (int)player.posX, (int)player.posY, (int)player.posZ);
        return new ActionResult<ItemStack>(EnumActionResult.FAIL,player.getHeldItem(hand));
    }
}