package com.squaresuits.magicalpotionsandbrews.crafting;

import com.squaresuits.magicalpotionsandbrews.init.MPBBlocks;
import com.squaresuits.magicalpotionsandbrews.init.MPBItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MPBSmelting {
	
	public static void initSmelting(){
		GameRegistry.addSmelting(MPBBlocks.copperOre, new ItemStack(MPBItems.copper_ingot), 0);
		GameRegistry.addSmelting(MPBBlocks.nickelOre, new ItemStack(MPBItems.nickel_ingot), 0);
		GameRegistry.addSmelting(MPBItems.pyrite_shard, new ItemStack(MPBItems.pyrite_ingot), 0);
	}
}
