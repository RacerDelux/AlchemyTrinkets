package com.squaresuits.magicalpotionsandbrews.init;

import java.util.HashMap;
import java.util.Map;

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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class Blocks {
	private static final Map<String,Block> allBlocks = new HashMap<>();
	
	
	
	//Ore
	public static Block copper_ore;	
	public static Block nickel_ore;
	public static Block pyrite_ore;
	
	//Gems
	public static Block topaz_ore;
	
	//Blocks
	public static Block pyrite_block;
	public static Block infused_glass_block;
	
	//Items
	public static Block copper_cauldron;
	
	public static void initBlocks(){
		
		
		
		copper_ore = createOre(Materials.copper);
		
		nickel_ore = createOre(Materials.nickel);
		
		topaz_ore = createOre(Materials.topaz);
		
		pyrite_ore = createOre(Materials.pyrite);
		pyrite_block = createBlock(Materials.pyrite);
		
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
		GameRegistry.register(block);
		
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(MPBGlobal.MOD_ID, name);
		GameRegistry.register(itemBlock);
		
		block.setCreativeTab(MPBGlobal.MyCrTab);
		
		allBlocks.put(name, block);
		return block;
	}
	
	private static Block regInfusedBlock(Block block, String name){
		block.setRegistryName(MPBGlobal.MOD_ID, name);
		block.setUnlocalizedName(MPBGlobal.MOD_ID+"."+name);
		GameRegistry.register(block);
		
		ItemInfusedGlassBlock itemBlock = new ItemInfusedGlassBlock(block);
		itemBlock.setRegistryName(MPBGlobal.MOD_ID, name);
		GameRegistry.register(itemBlock);
		
		block.setCreativeTab(MPBGlobal.MyCrTab);
		
		//allBlocks.put(name, block);



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
	
	@SideOnly(Side.CLIENT)
	public static void createCustomModels(){
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
