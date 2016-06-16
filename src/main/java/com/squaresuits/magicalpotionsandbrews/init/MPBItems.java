package com.squaresuits.magicalpotionsandbrews.init;

import java.util.HashMap;
import java.util.Map;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.items.MPBItemInfusedGlass;
import com.squaresuits.magicalpotionsandbrews.items.MPBItemFlaskComponent;
import com.squaresuits.magicalpotionsandbrews.items.MPBItemGem;
import com.squaresuits.magicalpotionsandbrews.items.MPBItemIngot;
import com.squaresuits.magicalpotionsandbrews.items.MPBItemPotionFlask;
import com.squaresuits.magicalpotionsandbrews.items.MPBItemShard;
import com.squaresuits.magicalpotionsandbrews.items.tools.MPBCopperPickaxe;
import com.squaresuits.magicalpotionsandbrews.material.MPBResourceMaterial;
import com.squaresuits.magicalpotionsandbrews.registry.MPBOreDictionaryEntry;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import scala.Console;

public class MPBItems {
	
	private static Map<Item,String> itemMPBRegistry = new HashMap<>();
	private static Map<String,Item> allMPBItems = new HashMap<>();
	
	//Flask
	public static Item potion_flask;
	
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
	
	//Flask Components
	public static Item copper_flask_component;
	public static Item iron_flask_component;
	public static Item gold_flask_component;
	
	public static Item starsteel_flask_component;
	
	public static void initItems(){
		
		
		//Flask
		potion_flask = createPotionFlask();
		//Tools
		//GameRegistry.registerItem(copperPickaxe = new MPBCopperPickaxe("copperPickaxe", COPPERTOOLS), "copperPickaxe");
		
		//Ingot
		pyrite_ingot = createIngot(MPBMaterial.pyrite);
		copper_ingot = createIngot(MPBMaterial.copper);
		nickel_ingot = createIngot(MPBMaterial.nickel);
		
		//Shards
		pyrite_shard = createShard(MPBMaterial.pyrite);
		
		//Gems
		topaz_stone = createGem(MPBMaterial.topaz);
		
		//Flask Components
		copper_flask_component = createFlaskComponent(MPBMaterial.copper);
		iron_flask_component = createFlaskComponent(MPBMaterial.vanilla_iron);
		gold_flask_component = createFlaskComponent(MPBMaterial.vanilla_gold);
		
		if (Loader.isModLoaded("basemetals")) {
            try {
            	starsteel_flask_component = createFlaskComponent(MPBMaterial.starsteel);
                Console.out().println("Base Metals found - recipes added!");
            }
            catch (Exception e) {
            	Console.out().println("Base Metals not found!");
                e.printStackTrace(System.err);
            }
        }
		
		for(Item i : itemMPBRegistry.keySet()){
			allMPBItems.put(itemMPBRegistry.get(i), i);
			if(i instanceof MPBOreDictionaryEntry){
				OreDictionary.registerOre(((MPBOreDictionaryEntry)i).getOreDictionaryName(), i);}
		}
		
	}
	
	private static Item createPotionFlask(){
		return regFlask(new MPBItemPotionFlask(), "potion_flask", MPBGlobal.MyCrTab);
	}
	
	private static Item createInfusedGlass(MPBResourceMaterial metal){
		return regItem(new MPBItemInfusedGlass(metal), metal.getName()+"_infused_glass", metal, MPBGlobal.MyCrTab);
	}
	
	private static Item createFlaskComponent(MPBResourceMaterial metal){
		return regItem(new MPBItemFlaskComponent(metal), metal.getName()+"_flask_component", metal, MPBGlobal.MyCrTab);
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
		itemMPBRegistry.put(item, name);
		if(tab != null){
			item.setCreativeTab(tab);
		}
		return item;
	}
	private static Item regFlask(Item item, String name, CreativeTabs tab){
		item.setRegistryName(MPBGlobal.MOD_ID, name);
		item.setUnlocalizedName(MPBGlobal.MOD_ID+"."+name);
		GameRegistry.register(item); 
		itemMPBRegistry.put(item, name);
		if(tab != null){
			item.setCreativeTab(tab);
		}
		return item;
	}
	
	@SideOnly(Side.CLIENT)
	public static void regItemRenders(FMLInitializationEvent event){
		for(Item i : itemMPBRegistry.keySet()){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(i, 0, 
				new ModelResourceLocation(MPBGlobal.MOD_ID+":"+itemMPBRegistry.get(i), "inventory"));
		}
	}
	
	public static Item getMPBItemByName(String name){
		return allMPBItems.get(name);
	}
}
