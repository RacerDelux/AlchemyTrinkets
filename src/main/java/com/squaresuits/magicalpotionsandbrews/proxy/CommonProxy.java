package com.squaresuits.magicalpotionsandbrews.proxy;

import com.squaresuits.magicalpotionsandbrews.crafting.MPBRecipes;
import com.squaresuits.magicalpotionsandbrews.crafting.MPBSmelting;
import com.squaresuits.magicalpotionsandbrews.init.MPBBlocks;
import com.squaresuits.magicalpotionsandbrews.init.MPBItems;
import com.squaresuits.magicalpotionsandbrews.init.MPBMaterial;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent preEvent){
		MPBMaterial.initMaterial();
		MPBItems.initItems();
		MPBBlocks.initBlocks();
		
		MPBRecipes.initRecipes();
		MPBSmelting.initSmelting();
	}
	
	public void init(FMLInitializationEvent event){
		
	}

	public void postInit(FMLPostInitializationEvent postEvent){
	
	}
}
