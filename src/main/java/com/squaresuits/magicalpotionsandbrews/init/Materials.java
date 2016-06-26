package com.squaresuits.magicalpotionsandbrews.init;

import com.squaresuits.magicalpotionsandbrews.material.ResourceMaterial;

import net.minecraftforge.fml.common.Loader;
import scala.Console;

public abstract class Materials {
	public static ResourceMaterial pyrite;
	public static ResourceMaterial copper;
	public static ResourceMaterial nickel;
	public static ResourceMaterial topaz;
	
	// vanilla imports
		public static ResourceMaterial vanilla_wood;
		public static ResourceMaterial vanilla_stone;
		public static ResourceMaterial vanilla_iron;
		public static ResourceMaterial vanilla_gold;
		public static ResourceMaterial vanilla_diamond;
		
		public static ResourceMaterial starsteel;
	
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
	
	private static ResourceMaterial addMaterial(String name, double hardness, double strength, double magic){
		ResourceMaterial m = new ResourceMaterial(name,(float)hardness,(float)strength,(float)magic);
		//registerMaterial(name, m);
		return m;
	}
}
