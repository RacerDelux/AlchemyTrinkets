package com.squaresuits.magicalpotionsandbrews.init;

import com.squaresuits.magicalpotionsandbrews.material.MPBResourceMaterial;

import net.minecraftforge.fml.common.Loader;
import scala.Console;

public abstract class MPBMaterial {
	public static MPBResourceMaterial pyrite;
	public static MPBResourceMaterial copper;
	public static MPBResourceMaterial nickel;
	public static MPBResourceMaterial topaz;
	
	// vanilla imports
		public static MPBResourceMaterial vanilla_wood;
		public static MPBResourceMaterial vanilla_stone;
		public static MPBResourceMaterial vanilla_iron;
		public static MPBResourceMaterial vanilla_gold;
		public static MPBResourceMaterial vanilla_diamond;
		
		public static MPBResourceMaterial starsteel;
	
	public static void initMaterial(){
		
		// vanilla metals
				vanilla_wood = addMaterial("wood",2,2,6);
				vanilla_stone = addMaterial("stone",5,4,2);
				vanilla_iron = addMaterial("iron",8,8,4.5);
				vanilla_gold = addMaterial("gold",1,1,10);
				vanilla_diamond = addMaterial("diamond",10,15,4);
				
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
	
	private static MPBResourceMaterial addMaterial(String name, double hardness, double strength, double magic){
		MPBResourceMaterial m = new MPBResourceMaterial(name,(float)hardness,(float)strength,(float)magic);
		//registerMaterial(name, m);
		return m;
	}
}
