package com.squaresuits.magicalpotionsandbrews.proxy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.crafting.Recipes;
import com.squaresuits.magicalpotionsandbrews.crafting.Smelting;
import com.squaresuits.magicalpotionsandbrews.init.Blocks;
import com.squaresuits.magicalpotionsandbrews.init.Fluids;
import com.squaresuits.magicalpotionsandbrews.init.Items;
import com.squaresuits.magicalpotionsandbrews.init.Materials;
import com.squaresuits.magicalpotionsandbrews.init.tconPlugin;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.MissingModsException;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;

public class CommonProxy {
	
	/** location of ore-spawn files */
	public static Path oreSpawnFolder = null;
	/** if true, then this mod will require the orespawn mod */
	public static boolean requireOreSpawn = true;
	
	public void preInit(FMLPreInitializationEvent preEvent){
		
		// load config
		Configuration config = new Configuration(preEvent.getSuggestedConfigurationFile());
		config.load();
		
		requireOreSpawn = config.getBoolean("using_orespawn", "options", requireOreSpawn,
				"If false, then Base Metals will not require DrCyano's Ore Spawn mod. \n" +
						"Set to false if using another mod to manually handle ore generation.");
		
		if(!requireOreSpawn) {
			if(!net.minecraftforge.fml.common.Loader.isModLoaded("orespawn")){
				HashSet<ArtifactVersion> orespawnMod = new HashSet<>();
				orespawnMod.add(new DefaultArtifactVersion("1.0.0"));
				throw new MissingModsException(orespawnMod, "orespawn", "DrCyano's Ore Spawn Mod");
			}
			oreSpawnFolder = Paths.get(preEvent.getSuggestedConfigurationFile().toPath().getParent().toString(), "orespawn");
			Path oreSpawnFile = Paths.get(oreSpawnFolder.toString(), MPBGlobal.MOD_ID + ".json");
			if (Files.exists(oreSpawnFile) == false) {
				try {
					Files.createDirectories(oreSpawnFile.getParent());
					Files.write(oreSpawnFile, Arrays.asList(MPBGlobal.defaultOreSpawnJSON.split("\n")), Charset.forName("UTF-8"));
				} catch (IOException e) {
					FMLLog.severe(MPBGlobal.MOD_ID + ": Error: Failed to write file " + oreSpawnFile);
				}
			}
		} else {
			oreSpawnFolder = Paths.get(preEvent.getSuggestedConfigurationFile().toPath().getParent().toString(), "orespawn");
			Path oreSpawnFile = Paths.get(oreSpawnFolder.toString(), MPBGlobal.MOD_ID + ".json");
			if (Files.exists(oreSpawnFile) == true) {
				try {
					Files.delete(oreSpawnFile.getParent());
				} catch (IOException e) {
					FMLLog.severe(MPBGlobal.MOD_ID + ": Error: Failed to delete file " + oreSpawnFile);
				}
			}
		}
		
		config.save();
		
		
		Materials.initMaterial();
		Items.initItems();
		Blocks.initBlocks();
		Fluids.initFluids();
		Recipes.initRecipes();
		Smelting.initSmelting();
		//Plugins
		if(Loader.isModLoaded("tconstruct")) {
			tconPlugin.initTconPlugin();
		}
	}
	
	public void init(FMLInitializationEvent event){
		
	}

	public void postInit(FMLPostInitializationEvent postEvent){
	
	}
}
