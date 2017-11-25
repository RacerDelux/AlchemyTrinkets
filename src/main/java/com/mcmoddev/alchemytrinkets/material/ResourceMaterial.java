package com.mcmoddev.alchemytrinkets.material;

import java.util.Locale;

public class ResourceMaterial {
	
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
	
	public ResourceMaterial(String name, float hardness, float strength, float magic){
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
	public String getCapitalizedName(){
		return titleName;
	}
	
	/**
	 * Gets the amount of XP per ore block that is smelted
	 * @return XP value per ore block
	 */
	public float getOreSmeltXP(){
		return 0.1f * magicAffinity;
	}
	
	/**
	 * Gets the hardness for blocks made from this material
	 * @return the hardness for blocks made from this material
	 */
	public float getMetalBlockHardness(){
		return 2.0f * hardness;
	}
	
	/**
	 * Gets the hardness of the ore block for this material
	 * @return the hardness of the ore block for this material
	 */
	public float getOreBlockHardness(){
		return 0.5f * hardness;
	}
	
	/**
	 * Gets the resistance of blocks made from this metal to explosions
	 * @return the blast resistance score
	 */
	public float getBlastResistance(){
		return 2.5f * strength;
	}
	
	public int getRequiredHarvestLevel(){
		return (int)clamp((0.9f*hardness / 3f),-1,3); 
	}
	
	static int clamp(int x, int min, int max){
		if(x < min)return min;
		if(x > max) return max;
		return x;
	}
	static float clamp(float x, float min, float max){
		if(x < min)return min;
		if(x > max) return max;
		return x;
	}
	static double clamp(double x, double min, double max){
		if(x < min)return min;
		if(x > max) return max;
		return x;
	}
}
