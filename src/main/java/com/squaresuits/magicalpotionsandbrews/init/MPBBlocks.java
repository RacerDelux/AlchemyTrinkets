package com.squaresuits.magicalpotionsandbrews.init;

import java.util.HashMap;
import java.util.Map;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.blocks.MPBBlockBlock;
import com.squaresuits.magicalpotionsandbrews.blocks.MPBBlockOre;
import com.squaresuits.magicalpotionsandbrews.items.MPBBItemInfusedGlass;
import com.squaresuits.magicalpotionsandbrews.material.MPBResourceMaterial;
import com.squaresuits.magicalpotionsandbrews.registry.MPBOreDictionaryEntry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class MPBBlocks {
	private static final Map<String,Block> allBlocks = new HashMap<>();
	
	
	
	//Ore
	public static Block copper_ore;	
	public static Block nickel_ore;
	public static Block pyrite_ore;
	
	//Gems
	public static Block topaz_ore;
	
	//Blocks
	public static Block pyrite_block;
	
	public static void initBlocks(){
		
		
		
		copper_ore = createOre(MPBMaterial.copper);
		
		nickel_ore = createOre(MPBMaterial.nickel);
		
		topaz_ore = createOre(MPBMaterial.topaz);
		
		pyrite_ore = createOre(MPBMaterial.pyrite);
		pyrite_block = createBlock(MPBMaterial.pyrite);
		

		for(Block b : allBlocks.values()){
			if(b instanceof MPBOreDictionaryEntry){
				OreDictionary.registerOre(((MPBOreDictionaryEntry)b).getOreDictionaryName(), b);}
		}
	}
	
	
	
	private static Block createBlock(MPBResourceMaterial metal){
		return createBlock(metal,false);
	}

	private static Block createBlock(MPBResourceMaterial metal, boolean glow){
		return regBlock(new MPBBlockBlock(metal,glow),metal.getName()+"_block");
	}
	
	private static Block createOre(MPBResourceMaterial metal){
		return regBlock(new MPBBlockOre(metal),metal.getName()+"_ore");
	}
	
	private static Block regBlock(Block block, String name){
		block.setRegistryName(MPBGlobal.MOD_ID, name);
		block.setUnlocalizedName(MPBGlobal.MOD_ID+"."+name);
		GameRegistry.register(block);
		
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(MPBGlobal.MOD_ID, name);
		GameRegistry.register(itemBlock);
		
		block.setCreativeTab(MPBGlobal.MyCrTab);
		
		allBlocks.put(name, block);
		return block;
	}
	
	@SideOnly(Side.CLIENT)
	public static void regBlockRenders(FMLInitializationEvent event){
		for(String name : allBlocks.keySet()){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(net.minecraft.item.Item.getItemFromBlock(allBlocks.get(name)), 0, 
				new ModelResourceLocation(MPBGlobal.MOD_ID+":"+name, "inventory"));
		}
	}
	
}
