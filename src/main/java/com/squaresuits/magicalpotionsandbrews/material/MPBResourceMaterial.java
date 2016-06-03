package com.squaresuits.magicalpotionsandbrews.material;

import java.util.Locale;

public class MPBResourceMaterial {
	
	/** hardness on a scale from 0 to 10 (or more), where 0 is non-solid and 
	 * diamond is 10. For reference, wood is 3, stone is 5, iron is 8, diamond is 10.
	 * */
	public final float hardness;
	/** durability on a scale from 0 to 10 (or more).
	 * For reference, leather is 2.5, gold is 3, wood is 2, stone is 4, iron is 8, minecraft diamond is 10.
	 *  */
	public final float strength;
	/**
	 * Scale from 0 to 10 (or more) on how magical the material is.
	 * For reference, stone is 2, iron is 4.5, diamond is 4, wood is 6, gold is 10.
	 */
	public final float magicAffinity;
	/**
	 * Rare metals, like platinum, are never found in villager trades and unusually uncommon
	 * in world generation
	 */
	public final boolean isRare;
	/**
	 * String used to identify items and blocks using this material
	 */
	final String identifier;
	
	final String titleName;
	
	public MPBResourceMaterial(String name, float hardness, float strength, float magic){
		this.hardness = hardness;
		this.strength = strength;
		this.magicAffinity = magic;
		this.identifier = name;
		String firstLetter = name.substring(0,1);
		String rest = name.substring(1);
		titleName = firstLetter.toUpperCase(Locale.ENGLISH)+rest;
		//enumName = (BaseMetals.MODID+"_"+name).toUpperCase(Locale.ENGLISH);
		isRare = false;
	}

	public String getName(){
		return identifier;
	}
}
