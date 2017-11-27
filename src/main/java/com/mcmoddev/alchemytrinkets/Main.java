package com.mcmoddev.alchemytrinkets;

import com.mcmoddev.alchemytrinkets.proxy.CommonProxy;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
		modid = MPBGlobal.MOD_ID,
		name = MPBGlobal.MOD_NAME,
		version = MPBGlobal.VERSION,
		acceptedMinecraftVersions = "[1.12,)",
		dependencies = "after:tconstruct;required-after:basemetals;before:orespawn")
public class Main {

	public  static final String MODID  = "magicpab";
	public static final String NAME = "Alchemy Trinkets";
	public static final String VERSION = "0.1.9.0";

	public static Logger logger;
	

	@Instance(MPBGlobal.MOD_ID)
	public static Main instance;
	
	@SidedProxy(clientSide = MPBGlobal.NG_CLIENT_PROXY, serverSide = MPBGlobal.NG_COMMON_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent){
		logger = preEvent.getModLog();
		Main.proxy.preInit(preEvent);
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		Main.proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent){
		Main.proxy.postInit(postEvent);
	}
}
