package com.squaresuits.magicalpotionsandbrews.init;

import java.util.HashMap;
import java.util.Map;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.items.ItemInfusedGlass;
import com.squaresuits.magicalpotionsandbrews.items.ItemFlaskComponent;
import com.squaresuits.magicalpotionsandbrews.items.ItemGem;
import com.squaresuits.magicalpotionsandbrews.items.ItemIngot;
import com.squaresuits.magicalpotionsandbrews.items.ItemMock;
import com.squaresuits.magicalpotionsandbrews.items.ItemPotionFlask;
import com.squaresuits.magicalpotionsandbrews.items.ItemShard;
import com.squaresuits.magicalpotionsandbrews.material.ResourceMaterial;
import com.squaresuits.magicalpotionsandbrews.registry.MPBOreDictionaryEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import scala.Console;

public class Items {
	
	private static Map<Item,String> itemMPBRegistry = new HashMap<>();
	private static Map<String,Item> allMPBItems = new HashMap<>();
	
	//Flask
	public static Item potion_flask;
	
	//mock
	public static Item potion_mock;
	public static Item component_mock;
	
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
	//public static Item pyrite_shard;
	
	//Ore
	public static Item topaz_stone;
	
	//Flask Components
	public static Item flask_component;
	
	public static void initItems(){
		
		
		//Flask
		potion_flask = createPotionFlask();
		
		//Mock
		potion_mock = createMockItem("potion");
		component_mock = createMockItem("component");
		//Tools
		//GameRegistry.registerItem(copperPickaxe = new MPBCopperPickaxe("copperPickaxe", COPPERTOOLS), "copperPickaxe");
		
		//Ingot
		pyrite_ingot = createIngot(Materials.pyrite);
		copper_ingot = createIngot(Materials.copper);
		nickel_ingot = createIngot(Materials.nickel);
		
		//Shards
		//pyrite_shard = createShard(MPBMaterial.pyrite);
		
		//Gems
		topaz_stone = createGem(Materials.topaz);
		
		//Flask Components
		flask_component = createFlaskComponent(Materials.copper);
		//iron_flask_component = createFlaskComponent(Materials.vanilla_iron);
		//gold_flask_component = createFlaskComponent(Materials.vanilla_gold);
		
		if (Loader.isModLoaded("basemetals")) {
            try {
            	//starsteel_flask_component = createFlaskComponent(Materials.starsteel);
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
	private static Item createMockItem(String item){
		return regItem(new ItemMock(), item+"_mock", null, MPBGlobal.MyCrTab);
	}
	
	private static Item createPotionFlask(){
		return regFlask(new ItemPotionFlask(), "potion_flask", MPBGlobal.MyCrTab);
	}
	
	private static Item createInfusedGlass(ResourceMaterial metal){
		return regItem(new ItemInfusedGlass(metal), metal.getName()+"_infused_glass", metal, MPBGlobal.MyCrTab);
	}
	
	private static Item createFlaskComponent(ResourceMaterial metal){
		return regItem(new ItemFlaskComponent(metal), "flask_component", metal, MPBGlobal.MyCrTab);
	}
	
	private static Item createIngot(ResourceMaterial metal){
		return regItem(new ItemIngot(metal), metal.getName()+"_"+"ingot", metal, MPBGlobal.MyCrTab);
	}
	
	private static Item createShard(ResourceMaterial metal){
		return regItem(new ItemShard(metal), metal.getName()+"_"+"shard", metal, MPBGlobal.MyCrTab);
	}
	
	private static Item createGem(ResourceMaterial metal){
		return regItem(new ItemGem(metal), metal.getName()+"_"+"gem", metal, MPBGlobal.MyCrTab);
	}
	
	private static Item regItem(Item item, String name, ResourceMaterial metal, CreativeTabs tab){
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
