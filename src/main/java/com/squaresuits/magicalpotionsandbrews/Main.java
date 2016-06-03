package com.squaresuits.magicalpotionsandbrews;

import com.squaresuits.magicalpotionsandbrews.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MPBGlobal.MOD_ID, name = MPBGlobal.MOD_NAME, version = MPBGlobal.VERSION)
public class Main {

	@Instance(MPBGlobal.MOD_ID)
	public static Main instance;
	
	@SidedProxy(clientSide = MPBGlobal.NG_CLIENT_PROXY, serverSide = MPBGlobal.NG_COMMON_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent){
		
		this.proxy.preInit(preEvent);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		this.proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent){
		this.proxy.postInit(postEvent);
	}
}
