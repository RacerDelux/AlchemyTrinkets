package com.squaresuits.magicalpotionsandbrews.init;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.material.MMDMaterial;
import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.blocks.BlockBlock;
import com.squaresuits.magicalpotionsandbrews.blocks.BlockInfusedGlass;
import com.squaresuits.magicalpotionsandbrews.blocks.BlockOres;
import com.squaresuits.magicalpotionsandbrews.items.ItemInfusedGlassBlock;
import com.squaresuits.magicalpotionsandbrews.material.ResourceMaterial;
import com.squaresuits.magicalpotionsandbrews.recipes.MPBOreDictionaryEntry;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class Blocks extends com.mcmoddev.lib.init.Blocks{
	private static final Map<String,Block> allBlocks = new HashMap<>();
	private static Map<ItemBlock,String> allBlockItemMPBRegistry = new HashMap<>();
	
	
	//Ore
	public static Block copper_ore;	
	public static Block nickel_ore;
	
	//Gems
	public static Block topaz_ore;
	
	//Blocks
	public static Block infused_glass_block;
	
	//Items
	public static Block copper_cauldron;
	
	public static void initBlocks(){
		MMDMaterial pyrite = Materials.getMaterialByName("pyrite");
		create(Names.BLOCK, pyrite, MPBGlobal.MyCrTab);
		create(Names.ORE, pyrite, MPBGlobal.MyCrTab);
		
		
		copper_ore = createOre(Materials.copper);
		
		nickel_ore = createOre(Materials.nickel);
		
		topaz_ore = createOre(Materials.topaz);
		
		infused_glass_block = createInfusedGlasBlock(false);
		
		//copper_cauldron = createCauldron(MPBMaterial.copper, false);


		allBlocks.values().stream().filter(block -> block instanceof MPBOreDictionaryEntry).forEach(b ->
				OreDictionary.registerOre(((MPBOreDictionaryEntry) b).getOreDictionaryName(), b));
	}
	
	private static Block createInfusedGlasBlock(boolean ignoreSimilarity){
		return regInfusedBlock(new BlockInfusedGlass(ignoreSimilarity),"infused_glass_block");
	}
	
	/*private static Block createGlasBlock(ResourceMaterial metal, boolean ignoreSimilarity){
		return regBlock(new GlassBlock(metal,ignoreSimilarity),metal.getName()+"_glass_block");
	}*/
	
	private static Block createBlock(ResourceMaterial metal){
		return createBlock(metal,false);
	}

	private static Block createBlock(ResourceMaterial metal, boolean glow){
		return regBlock(new BlockBlock(metal,glow),metal.getName()+"_block");
	}
	
	/*private static Block createCauldron(ResourceMaterial metal, boolean glow){
		return regBlock(new BlockBlock(metal,glow),metal.getName()+"_cauldron");
	}*/
	
	private static Block createOre(ResourceMaterial metal){
		return regBlock(new BlockOres(metal),metal.getName()+"_ore");
	}
	
	private static Block regBlock(Block block, String name){
		block.setRegistryName(MPBGlobal.MOD_ID, name);
		block.setUnlocalizedName(MPBGlobal.MOD_ID+"."+name);
		
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(MPBGlobal.MOD_ID, name);
		
		block.setCreativeTab(MPBGlobal.MyCrTab);
		
		allBlocks.put(name, block);
		allBlockItemMPBRegistry.put(itemBlock, name);
		return block;
	}
	
	private static Block regInfusedBlock(Block block, String name){
		block.setRegistryName(MPBGlobal.MOD_ID, name);
		block.setUnlocalizedName(MPBGlobal.MOD_ID+"."+name);
		
		ItemInfusedGlassBlock itemBlock = new ItemInfusedGlassBlock(block);
		itemBlock.setRegistryName(MPBGlobal.MOD_ID, name);

		allBlocks.put(name, block);
		allBlockItemMPBRegistry.put(itemBlock, name);

		block.setCreativeTab(MPBGlobal.MyCrTab);
		
		//allBlocks.put(name, block);



		return block;
	}

	/*
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for( MMDMaterial mat : Materials.getMaterialsByMod(MPBGlobal.MOD_ID) ) {
			for( Block block : mat.getBlocks() ) {
				if( block.getRegistryName().getResourceDomain().equals(MPBGlobal.MOD_ID) ) {
					event.getRegistry().register(block);
				}
			}
		}
	}*/
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for( Entry<String, Block> ent : allBlocks.entrySet() ) {
			event.getRegistry().register(ent.getValue());
		}
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for( Entry<ItemBlock, String> ent : allBlockItemMPBRegistry.entrySet() ) {
			event.getRegistry().register(ent.getKey());
		}
	}

	@SideOnly(Side.CLIENT)
	public static void regBlockRenders(FMLInitializationEvent event){


		for(String name : allBlocks.keySet()){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(net.minecraft.item.Item.getItemFromBlock(allBlocks.get(name)), 0, 
				new ModelResourceLocation(MPBGlobal.MOD_ID+":"+name, "inventory"));
		}

	}
	
	@SideOnly(Side.CLIENT)
	public static void createCustomModels(){


	}

	@SubscribeEvent
	public void modelRegistryBits(ModelRegistryEvent ev) {
		ResourceLocation test = new ResourceLocation("magicpab:infused_glass_block");
		Item itemBlockVariants = Item.REGISTRY.getObject(test);
		//GameRegistry.findItem("magicpab", "infused_glass_block");

		ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("magicpab:diamond_infused_glass_block", "inventory");
		ModelLoader.setCustomModelResourceLocation(itemBlockVariants, BlockInfusedGlass.EnumMat.DIAMOND.getMetadata(), itemModelResourceLocation);

		itemModelResourceLocation = new ModelResourceLocation("magicpab:pyrite_infused_glass_block", "inventory");
		ModelLoader.setCustomModelResourceLocation(itemBlockVariants, BlockInfusedGlass.EnumMat.PYRITE.getMetadata(), itemModelResourceLocation);

		itemModelResourceLocation = new ModelResourceLocation("magicpab:emerald_infused_glass_block", "inventory");
		ModelLoader.setCustomModelResourceLocation(itemBlockVariants, BlockInfusedGlass.EnumMat.EMERALD.getMetadata(), itemModelResourceLocation);
	}
}
