package com.squaresuits.magicalpotionsandbrews.util;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.LinkedHashMap;

public class FlaskUtil {
	public static final int MATINT = 0;
	public static final int MATUSE = 1;
	public static final int MATCOLOR = 2;
	
	public static final int GLASSMETA = 0;
	
	public static ArrayList<String> flaskMaterials = new ArrayList<>(asList("iron", "gold"));
	public static ArrayList<String> glassMaterials = new ArrayList<>(asList("pyrite", "diamond")); 
	public static LinkedHashMap<String, Integer[]> flaskMaterialInfo = new LinkedHashMap<String, Integer[]>(){{
		//								INT USE	Color
		put("copper",new Integer[] 		{0,	4,	0xEDA726});
		put("iron",new Integer[] 		{1,	5,	0xB0B0B0});
		put("gold",new Integer[] 		{2,	10,	0xE8DA10});
		put("diamond",new Integer[]		{3, 15, 0x1BDEBD}); //Legacy
		put("starsteel",new Integer[] 	{4,	25,	0x1C1C1C});
		put("fyrestone", new Integer[]	{5, 6,	0xDB4725});
		put("earthstone", new Integer[]	{6, 15, 0xA8840C});
	}};
	public static LinkedHashMap<String, Integer[]> flaskGlassInfo = new LinkedHashMap<String, Integer[]>(){{
		//								META
		put("pyrite",new Integer[] 		{0});
		put("diamond",new Integer[] 	{1});
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
