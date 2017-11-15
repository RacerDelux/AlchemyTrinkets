package com.squaresuits.magicalpotionsandbrews.init;

import com.mcmoddev.lib.material.MMDMaterial;
import com.squaresuits.magicalpotionsandbrews.material.ResourceMaterial;

import net.minecraftforge.fml.common.Loader;
import scala.Console;

public abstract class Materials extends com.mcmoddev.lib.init.Materials{
	public static ResourceMaterial pyrite;
	public static ResourceMaterial copper;
	public static ResourceMaterial nickel;
	public static ResourceMaterial topaz;
		
		public static ResourceMaterial starsteel;
	
	public static void initMaterial(){
		createMaterial("pyrite", MMDMaterial.MaterialType.METAL, 3.5, 3, 9, 0x996868FF);
		pyrite = addMaterial("pyrite", 3.5,3,9);
		copper = addMaterial("copper", 3.5,3,9);
		nickel = addMaterial("nickel", 3.5,3,9);
		topaz = addMaterial("topaz", 3.5,3,9);
		
		if (Loader.isModLoaded("basemetals")) {
            try {
            	starsteel = addMaterial("starsteel", 10, 25, 12);
                Console.out().println("Base Metals found - recipes added!");
            }
            catch (Exception e) {
            	Console.out().println("Base Metals not found!");
                e.printStackTrace(System.err);
            }
        }
	}
	
	private static ResourceMaterial addMaterial(String name, double hardness, double strength, double magic){
		ResourceMaterial m = new ResourceMaterial(name,(float)hardness,(float)strength,(float)magic);
		//registerMaterial(name, m);
		return m;
	}
}
