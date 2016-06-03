package com.squaresuits.magicalpotionsandbrews.init;

import java.util.HashMap;
import java.util.Map;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.items.MPBItem;
import com.squaresuits.magicalpotionsandbrews.items.MPBItemGem;
import com.squaresuits.magicalpotionsandbrews.items.MPBItemIngot;
import com.squaresuits.magicalpotionsandbrews.items.MPBItemShard;
import com.squaresuits.magicalpotionsandbrews.items.tools.MPBCopperPickaxe;
import com.squaresuits.magicalpotionsandbrews.material.MPBResourceMaterial;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MPBItems {
	
	private static Map<Item,String> itemRegistry = new HashMap<>();
	
	//Tools
	public static ToolMaterial COPPERTOOLS = EnumHelper.addToolMaterial("COPPERTOOLS", 2, 350, 5.0F, 2.2F, 15);
	
	public static Item copperPickaxe;
	public static Item copperAxe;
	public static Item copperSpade;
	public static Item copperHoe;
	public static Item copperSword;
	
	//Ingots
	public static Item copper_ingot;
	public static Item nickel_ingot;
	public static Item pyrite_ingot;
	
	//Shards
	public static Item pyrite_shard;
	
	//Ore
	public static Item topaz_stone;
	
	public static void initItems(){
		
		//Tools
		GameRegistry.registerItem(copperPickaxe = new MPBCopperPickaxe("copperPickaxe", COPPERTOOLS), "copperPickaxe");
		
		//Ingot
		pyrite_ingot = createIngot(MPBMaterial.pyrite);
		copper_ingot = createIngot(MPBMaterial.copper);
		nickel_ingot = createIngot(MPBMaterial.nickel);
		
		//Shards
		pyrite_shard = createShard(MPBMaterial.pyrite);
		
		//Gems
		topaz_stone = createGem(MPBMaterial.topaz);
	}
	
	
	private static Item createIngot(MPBResourceMaterial metal){
		return regItem(new MPBItemIngot(metal), metal.getName()+"_"+"ingot", metal, MPBGlobal.MyCrTab);
	}
	
	private static Item createShard(MPBResourceMaterial metal){
		return regItem(new MPBItemShard(metal), metal.getName()+"_"+"shard", metal, MPBGlobal.MyCrTab);
	}
	
	private static Item createGem(MPBResourceMaterial metal){
		return regItem(new MPBItemGem(metal), metal.getName()+"_"+"gem", metal, MPBGlobal.MyCrTab);
	}
	
	private static Item regItem(Item item, String name, MPBResourceMaterial metal, CreativeTabs tab){
		item.setRegistryName(MPBGlobal.MOD_ID, name);
		item.setUnlocalizedName(MPBGlobal.MOD_ID+"."+name);
		GameRegistry.register(item); 
		itemRegistry.put(item, name);
		if(tab != null){
			item.setCreativeTab(tab);
		}
		return item;
	}
	
	
	@SideOnly(Side.CLIENT)
	public static void regItemRenders(FMLInitializationEvent event){
		for(Item i : itemRegistry.keySet()){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(i, 0, 
				new ModelResourceLocation(MPBGlobal.MOD_ID+":"+itemRegistry.get(i), "inventory"));
		}
	}
}
