package com.mcmoddev.alchemytrinkets.init;

import java.util.Arrays;
import java.util.List;

import com.mcmoddev.alchemytrinkets.MPBCreativeTab;
import com.mcmoddev.alchemytrinkets.MPBGlobal;
import com.mcmoddev.alchemytrinkets.items.*;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.util.Oredicts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class Items extends com.mcmoddev.lib.init.Items{
	private static boolean initDone = false;

	//Book
	public static Item alchemy_book;

	//Flask
	public static Item potion_flask;
	
	//mock
	public static Item potion_mock;
	public static Item component_mock;
	
	//Shards
	//public static Item pyrite_shard;
	
	//Ore
	public static Item topaz_stone;
	
	//Flask Components
	public static Item flask_component;
	
	public static void init() {
		if( initDone ) return;
		
		Materials.init();
		Blocks.init();
		
		List<String> ingotMaterials = Arrays.asList( /*"copper", "nickel",*/ "pyrite");
		
		ingotMaterials.forEach( name -> create( Names.INGOT, Materials.getMaterialByName(name), MPBGlobal.MyCrTab) );
		
		MMDMaterial pyrite = Materials.getMaterialByName("pyrite");
		create(Names.PICKAXE, pyrite, MPBGlobal.MyCrTab);
		create(Names.AXE, pyrite, MPBGlobal.MyCrTab);
		create(Names.HOE, pyrite, MPBGlobal.MyCrTab);
		create(Names.SWORD, pyrite, MPBGlobal.MyCrTab);

		//Book
		alchemy_book = makeBook("alchemy_book", 0);

		//Flask
		potion_flask = makePotionFlask("");
		
		//Mock
		potion_mock = makeMockItem("potion");		
		component_mock = makeMockItem("component");
		
		//Tools
		//GameRegistry.registerItem(copperPickaxe = new MPBCopperPickaxe("copperPickaxe", COPPERTOOLS), "copperPickaxe");
		
		//Shards
		//pyrite_shard = createShard(MPBMaterial.pyrite);
		
		//Gems
		topaz_stone = makeItemGem( "topaz" );
		
		//Flask Components
		flask_component = makeFlaskComponent("");

		ItemPotionFlask flask = (ItemPotionFlask) Items.potion_flask;
		if (Loader.isModLoaded("basemetals")) {
			flask.flaskMaterials.add("copper");
			flask.flaskMaterials.add("starsteel");
		}

		initDone = true;
	}

	private static void setupItem( Item item, ResourceLocation registryName, String unloc, MPBCreativeTab tab) {
		item.setRegistryName(registryName);
		item.setUnlocalizedName(unloc);
		item.setCreativeTab(tab);
	}
	
	private static ItemFlaskComponent makeFlaskComponent(String name ) {
		ItemFlaskComponent item = new ItemFlaskComponent();
		String actName;

		//if( name != null && name.length() > 0 )
		//	actName = String.format("%s_flask_component", name);
		//else
			actName = "flask_component";

		String unloc = String.format("%s.%s", MPBGlobal.MOD_ID, actName);
		ResourceLocation regName = new ResourceLocation(MPBGlobal.MOD_ID, actName);
		setupItem( item, regName, unloc, MPBGlobal.MyCrTab );
		return item;				
	}
	
	private static ItemPotionFlask makePotionFlask(String name ) {
		ItemPotionFlask item = new ItemPotionFlask();
		String actName;
		
		if( name != null && name.length() > 0 )
			actName = String.format("%s_potion_flask", name);
		else
			actName = "potion_flask";
		
		String unloc = String.format("%s.%s", MPBGlobal.MOD_ID, actName);
		ResourceLocation regName = new ResourceLocation(MPBGlobal.MOD_ID, actName);
		setupItem( item, regName, unloc, MPBGlobal.MyCrTab );
		return item;
	}
	
	private static ItemMock makeMockItem(String name ) {
		ItemMock item = new ItemMock();
		String actName = String.format("%s_mock", name);
		String unloc = String.format("%s.%s", MPBGlobal.MOD_ID, actName);
		ResourceLocation regName = new ResourceLocation(MPBGlobal.MOD_ID, actName);
		setupItem( item, regName, unloc, MPBGlobal.MyCrTab );
		return item;		
	}

	private static ItemBook makeBook(String name, int GUI){
		ItemBook item = new ItemBook(name, GUI);
		String actName = String.format("%s_book", name);
		String unloc = String.format("%s.%s", MPBGlobal.MOD_ID, actName);
		ResourceLocation regName = new ResourceLocation(MPBGlobal.MOD_ID, actName);
		setupItem( item, regName, unloc, MPBGlobal.MyCrTab );
		return item;
	}
	
	private static ItemGem makeItemGem(String name ) {
		ItemGem item = new ItemGem(Materials.getMaterialByName(name));
		String actName = String.format("%s_gem", name);
		String unloc = String.format("%s.%s", MPBGlobal.MOD_ID, actName);
		ResourceLocation regName = new ResourceLocation(MPBGlobal.MOD_ID, actName);
		setupItem( item, regName, unloc, MPBGlobal.MyCrTab );
		return item;
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		List<Item> items = Arrays.asList( potion_flask, potion_mock, component_mock, topaz_stone, flask_component, alchemy_book );
		items.forEach( event.getRegistry()::register );

		Materials.getAllMaterials().forEach( mat ->
		mat.getItems().forEach( item -> {
			if( item.getRegistryName().getResourceDomain().equals(MPBGlobal.MOD_ID) )
				event.getRegistry().register(item);			
		}));

		event.getRegistry().register(Blocks.infused_glass_item_block);

		Oredicts.registerItemOreDictionaryEntries();
		Oredicts.registerBlockOreDictionaryEntries();
	}

	@SideOnly(Side.CLIENT)
	public static void regItemRenders(FMLInitializationEvent event){
		Materials.getAllMaterials().forEach( mat ->
		mat.getItems().forEach( item -> {
			if( item.getRegistryName().getResourceDomain().equals(MPBGlobal.MOD_ID) )
				Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(item, 0,
					new ModelResourceLocation(MPBGlobal.MOD_ID+":"+item.getRegistryName().getResourcePath(), "inventory"));
		}));

		List<Item> items = Arrays.asList( potion_flask, potion_mock, component_mock, topaz_stone, flask_component, alchemy_book );
		items.forEach( item -> {
			ResourceLocation newLoc = new ResourceLocation(MPBGlobal.MOD_ID, item.getRegistryName().getResourcePath());
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(newLoc, "inventory"));
		});
	}
}
