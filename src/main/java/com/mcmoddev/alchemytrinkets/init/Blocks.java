package com.mcmoddev.alchemytrinkets.init;

import java.util.List;
import java.util.Arrays;

import com.mcmoddev.alchemytrinkets.MPBGlobal;
import com.mcmoddev.alchemytrinkets.items.ItemInfusedGlassBlock;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.alchemytrinkets.blocks.BlockInfusedGlass;

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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class Blocks extends com.mcmoddev.lib.init.Blocks{
	protected static Block infused_glass_block;
	protected static ItemInfusedGlassBlock infused_glass_item_block;
	
	private static boolean initDone = false;
	
	public static void init(){
		if( initDone ) return;
		
		// make sure this is around
		Materials.init();
		List<String> oreAndBlockMaterials = Arrays.asList( "pyrite", /*"copper", "nickel",*/ "topaz");
		oreAndBlockMaterials.forEach( name -> {
			create(Names.BLOCK, name, MPBGlobal.MyCrTab);
			create(Names.ORE, name, MPBGlobal.MyCrTab);
		});
		
		infused_glass_block = new BlockInfusedGlass(false);
		infused_glass_block.setRegistryName("infused_glass_block");
		infused_glass_block.setUnlocalizedName(MPBGlobal.MOD_ID+".infused_glass_block");
		infused_glass_item_block = new ItemInfusedGlassBlock(infused_glass_block);
		infused_glass_item_block.setRegistryName("infused_glass_block");
		infused_glass_item_block.setUnlocalizedName(MPBGlobal.MOD_ID+".infused_glass_block");
		
		initDone = true;
	}
	
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		Materials.getMaterialsByMod(MPBGlobal.MOD_ID).forEach( mat ->
		mat.getBlocks().forEach( block -> {
			if( block.getRegistryName().getResourceDomain().equals(MPBGlobal.MOD_ID) )
				event.getRegistry().register(block);
		}));

		event.getRegistry().register(infused_glass_block);

	}
	

	@SideOnly(Side.CLIENT)
	public static void regBlockRenders(FMLInitializationEvent event){
		Materials.getMaterialsByMod(MPBGlobal.MOD_ID).forEach( mat ->
		mat.getBlocks().forEach( block ->
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation(block.getRegistryName(), "inventory"))));
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
