package com.squaresuits.magicalpotionsandbrews.crafting;

import com.squaresuits.magicalpotionsandbrews.init.Blocks;
import com.squaresuits.magicalpotionsandbrews.init.Items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Smelting {
	
	public static void initSmelting(){
		GameRegistry.addSmelting(Blocks.copper_ore, new ItemStack(Items.copper_ingot), 1);
		GameRegistry.addSmelting(Blocks.nickel_ore, new ItemStack(Items.nickel_ingot), 1);
		GameRegistry.addSmelting(Blocks.pyrite_ore, new ItemStack(Items.pyrite_ingot), 10);
	}
}
