package com.squaresuits.magicalpotionsandbrews.proxy;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.init.MPBBlocks;
import com.squaresuits.magicalpotionsandbrews.init.MPBFluids;
import com.squaresuits.magicalpotionsandbrews.init.MPBItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
	public void preInit(FMLPreInitializationEvent preEvent){
		super.preInit(preEvent);
		
		MPBFluids.bakeModels(MPBGlobal.MOD_ID);
	}
	
	public void init(FMLInitializationEvent event){
		super.init(event);
		

		MPBItems.regItemRenders(event);
		MPBBlocks.regBlockRenders(event);
		
	}

	public void postInit(FMLPostInitializationEvent postEvent){
		super.postInit(postEvent);
	}
}
