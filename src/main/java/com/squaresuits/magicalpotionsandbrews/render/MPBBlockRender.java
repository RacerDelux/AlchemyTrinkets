package com.squaresuits.magicalpotionsandbrews.render;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.init.MPBBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class MPBBlockRender {
	
	public static void registerBlockRender() {
		
		//Ore
		regBlock(MPBBlocks.copperOre);
		regBlock(MPBBlocks.nickelOre);
		regBlock(MPBBlocks.pyriteOre);
		
		//Gems
		regBlock(MPBBlocks.topazOre);
		
		//Blocks
		regBlock(MPBBlocks.pyriteBlock);
	}
	
	public static void regBlock(Block block){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block),0, new ModelResourceLocation(MPBGlobal.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
}
