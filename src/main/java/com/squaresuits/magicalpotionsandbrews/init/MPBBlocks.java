package com.squaresuits.magicalpotionsandbrews.init;

import com.squaresuits.magicalpotionsandbrews.blocks.MPBBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MPBBlocks {
	
	//Ore
	public static Block copperOre;	
	public static Block nickelOre;
	public static Block pyriteOre;
	
	//Gems
	public static Block topazOre;
	
	//Blocks
	public static Block pyriteBlock;
	
	public static void initBlocks(){
		
		//Ore
		GameRegistry.registerBlock(copperOre = new MPBBlock("copperOre", Material.ROCK, 3, 15), "copperOre");
		GameRegistry.registerBlock(nickelOre = new MPBBlock("nickelOre", Material.ROCK, 3, 15), "nickelOre");
		GameRegistry.registerBlock(pyriteOre = new MPBBlock("pyriteOre", Material.ROCK, 3, 15), "pyriteOre");
		
		//Gems
		GameRegistry.registerBlock(topazOre = new MPBBlock("topazOre", Material.ROCK, 3, 15), "topazOre");
		
		//Blocks
		GameRegistry.registerBlock(pyriteBlock = new MPBBlock("pyriteBlock", Material.ROCK, 3, 15), "pyriteBlock");
	}
	
}
