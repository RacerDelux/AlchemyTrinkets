package com.squaresuits.magicalpotionsandbrews.proxy;

import com.squaresuits.magicalpotionsandbrews.init.MPBItems;
import com.squaresuits.magicalpotionsandbrews.render.MPBBlockRender;
import com.squaresuits.magicalpotionsandbrews.render.MPBItemRender;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
	public void preInit(FMLPreInitializationEvent preEvent){
		super.preInit(preEvent);
	}
	
	public void init(FMLInitializationEvent event){
		super.init(event);
		
		MPBBlockRender.registerBlockRender();
		MPBItemRender.registerItemRender();
		
		MPBItems.regItemRenders(event);
	}

	public void postInit(FMLPostInitializationEvent postEvent){
		super.postInit(postEvent);
	}
}
