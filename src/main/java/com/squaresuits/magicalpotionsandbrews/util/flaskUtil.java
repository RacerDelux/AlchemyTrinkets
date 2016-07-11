package com.squaresuits.magicalpotionsandbrews.util;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.LinkedHashMap;

import net.minecraft.util.text.TextFormatting;

public class FlaskUtil {
	//Material constants//
	public static final int COPPER 			= 0;
	public static final int IRON 			= 1;
	public static final int GOLD 			= 2;
	public static final int DIAMOND 		= 3;
	public static final int STARSTEEL 		= 4;
	public static final int FYRESTONE 		= 5;
	public static final int EARTHSTONE 		= 6;
	
	
	public static final int MATINT = 0;
	public static final int MATUSE = 1;
	public static final int MATCOLOR = 2;
	
	public static final int GLASSMETA = 0;
	
	public static ArrayList<String> flaskMaterials = new ArrayList<>(asList("iron", "gold"));
	public static ArrayList<String> glassMaterials = new ArrayList<>(asList("pyrite", "diamond")); 
	public static LinkedHashMap<String, Integer[]> flaskMaterialInfo = new LinkedHashMap<String, Integer[]>(){{
		//								INT 			USE	Color
		put("copper",new Integer[] 		{COPPER		,	4,	0xEDA726});
		put("iron",new Integer[] 		{IRON		,	5,	0xB0B0B0});
		put("gold",new Integer[] 		{GOLD		,	10,	0xE8DA10});
		put("diamond",new Integer[]		{DIAMOND	, 	15, 0x1BDEBD}); //Legacy
		put("starsteel",new Integer[] 	{STARSTEEL	,	25,	0x1C1C1C});
		put("fyrestone", new Integer[]	{FYRESTONE	,	 6,	0xDB4725});
		put("earthstone", new Integer[]	{EARTHSTONE	, 	15, 0xA8840C});
	}};
	public static LinkedHashMap<String, Integer[]> flaskGlassInfo = new LinkedHashMap<String, Integer[]>(){{
		//								META
		put("pyrite",new Integer[] 		{0});
		put("diamond",new Integer[] 	{1});
	}};
	public static LinkedHashMap<String, String> effectName = new LinkedHashMap<String, String>(){{

		put("copper", "");
		put("iron", "");
		put("gold", TextFormatting.GREEN + "Lucky");
		put("starsteel", TextFormatting.LIGHT_PURPLE + "Plentyful");
		put("fyrestone", "");
		put("earthstone", "");
		put("pyrite", "");
		put("diamond","");
	}};
	
	public static String getNameFromMeta(int meta){
		switch(meta){
		case 0:
			return "pyrite";
		case 1:
			return "diamond";
		default:
			return "none";
		}
	}

}
