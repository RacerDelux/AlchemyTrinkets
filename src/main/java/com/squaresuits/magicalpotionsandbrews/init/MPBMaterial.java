package com.squaresuits.magicalpotionsandbrews.init;

import com.squaresuits.magicalpotionsandbrews.material.MPBResourceMaterial;

public abstract class MPBMaterial {
	public static MPBResourceMaterial pyrite;
	public static MPBResourceMaterial copper;
	public static MPBResourceMaterial nickel;
	public static MPBResourceMaterial topaz;
	
	public static void initMaterial(){
		pyrite = addMaterial("pyrite", 3.5,3,9);
		copper = addMaterial("copper", 3.5,3,9);
		nickel = addMaterial("nickel", 3.5,3,9);
		topaz = addMaterial("topaz", 3.5,3,9);
	}
	
	private static MPBResourceMaterial addMaterial(String name, double hardness, double strength, double magic){
		MPBResourceMaterial m = new MPBResourceMaterial(name,(float)hardness,(float)strength,(float)magic);
		//registerMaterial(name, m);
		return m;
	}
}
