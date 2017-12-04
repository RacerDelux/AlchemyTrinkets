package com.mcmoddev.alchemytrinkets.proxy;

import java.nio.file.Path;
import java.util.HashSet;

import com.mcmoddev.alchemytrinkets.event.MPBEventHandler;
import com.mcmoddev.alchemytrinkets.init.Blocks;
import com.mcmoddev.alchemytrinkets.init.Fluids;
import com.mcmoddev.alchemytrinkets.init.Items;
import com.mcmoddev.alchemytrinkets.init.Materials;

import com.mcmoddev.alchemytrinkets.packet.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.MissingModsException;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
import net.minecraftforge.fml.server.FMLServerHandler;

public class CommonProxy implements MPBProxy {

	/** if true, then this mod will require the orespawn mod */
	private static boolean requireOreSpawn = true;
	
	public void preInit(FMLPreInitializationEvent preEvent){
		
		// load config
		Configuration config = new Configuration(preEvent.getSuggestedConfigurationFile());
		config.load();
		
		requireOreSpawn = config.getBoolean("using_orespawn", "options", requireOreSpawn,
				"If false, then Base Metals will not require DrCyano's Ore Spawn mod. \n" +
						"Set to false if using another mod to manually handle ore generation.");
		
		/* location of ore-spawn files */
		Path oreSpawnFolder;
		
		config.save();
		
		
		Materials.init();
		Items.init();
		Blocks.init();
		Fluids.init();

		//Plugins
		if(Loader.isModLoaded("tconstruct")) {
			//tconPlugin.initTconPlugin();
		}

		MinecraftForge.EVENT_BUS.register(Fluids.class);

		PacketHandler.preInit();
	}
	
	public void init(FMLInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new MPBEventHandler());
	}

	public void postInit(FMLPostInitializationEvent postEvent){
	
	}

	@Override
	public EntityPlayer getPlayer() {
		return null;
	}

	@Override
	public World getWorld() {
		return FMLServerHandler.instance().getServer().getEntityWorld();
	}
}
