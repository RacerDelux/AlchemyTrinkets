package com.squaresuits.magicalpotionsandbrews.util;

import java.util.LinkedHashMap;

public class FlaskUtil {
	public static final int MATINT = 0;
	public static final int MATUSE = 1;
	public static final int MATCOLOR = 2;
	
	public static String[] flaskMaterials = {"copper", "iron", "gold", "starsteel"};
	public static String[] glassMaterials = {"diamond"};
	public static LinkedHashMap<String, Integer[]> flaskMaterialInfo = new LinkedHashMap<String, Integer[]>(){{
		//								INT USE	Color
		put("copper",new Integer[] 		{0,	4,	0xEDA726});
		put("iron",new Integer[] 		{1,	5,	0xB0B0B0});
		put("gold",new Integer[] 		{2,	10,	0xE8DA10});
		put("diamond",new Integer[]		{3, 15, 0x1BDEBD}); //Legacy
		put("starsteel",new Integer[] 	{4,	25,	0x1C1C1C});
	}};
	/*public static LinkedHashMap<String, Integer> materialInt = new LinkedHashMap<String, Integer>(){{
		put("copper",0);
		put("iron",1);
	    put("gold",2);
	    put("diamond",3);
		put("starsteel",4);
	}};
	public static LinkedHashMap<String, Integer> materialUses = new LinkedHashMap<String, Integer>(){{
		put("copper",4);
		put("iron",5);
	    put("gold",10);
	    put("diamond",15);
		put("starsteel",25);
	}};
	public static LinkedHashMap<String, Integer> materialColor = new LinkedHashMap<String, Integer>(){{
		put("copper",0xEDA726);
	    put("iron",0xB0B0B0);
	    put("gold",0xE8DA10);
	    put("diamond",0x1BDEBD);
		put("starsteel",0x1C1C1C);
		put("pyrite",0xC4B012);
	}};*/

}
