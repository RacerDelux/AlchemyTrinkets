package com.mcmoddev.alchemytrinkets.crafting;

import com.mcmoddev.basemetals.data.MaterialNames;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.init.Materials;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Smelting {
	
	public static void initSmelting(){
		Block copperOre = Materials.getMaterialByName(MaterialNames.COPPER).getBlock(Names.ORE);
		Item copperIngot = Materials.getMaterialByName(MaterialNames.COPPER).getItem(Names.INGOT);
		Block nickelOre = Materials.getMaterialByName(MaterialNames.NICKEL).getBlock(Names.ORE);
		Item nickelIngot = Materials.getMaterialByName(MaterialNames.NICKEL).getItem(Names.INGOT);

		GameRegistry.addSmelting( copperOre, new ItemStack( copperIngot), 1 );
		GameRegistry.addSmelting( nickelOre, new ItemStack(nickelIngot), 1 );
	}
}
