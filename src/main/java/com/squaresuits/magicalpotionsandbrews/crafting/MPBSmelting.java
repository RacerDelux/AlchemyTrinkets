package com.squaresuits.magicalpotionsandbrews.crafting;

import com.squaresuits.magicalpotionsandbrews.blocks.MPBBlockOre;
import com.squaresuits.magicalpotionsandbrews.init.MPBBlocks;
import com.squaresuits.magicalpotionsandbrews.init.MPBItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MPBSmelting {
	
	public static void initSmelting(){
		GameRegistry.addSmelting(MPBBlocks.copper_ore, new ItemStack(MPBItems.copper_ingot), 1);
		GameRegistry.addSmelting(MPBBlocks.nickel_ore, new ItemStack(MPBItems.nickel_ingot), 1);
		GameRegistry.addSmelting(MPBBlocks.pyrite_ore, new ItemStack(MPBItems.pyrite_ingot), 10);
	}
}
