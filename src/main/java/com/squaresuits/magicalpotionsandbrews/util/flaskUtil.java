package com.squaresuits.magicalpotionsandbrews.util;

import java.util.HashMap;

public class flaskUtil {
	public static HashMap<String, Integer> materialUses = new HashMap<String, Integer>(){{
		put("copper",4);
		put("iron",5);
	    put("gold",10);
	    put("diamond",15);
		put("starsteel",25);
	}};
	public static HashMap<String, Integer> materialColor = new HashMap<String, Integer>(){{
		put("copper",0xEDA726);
	    put("iron",0xB0B0B0);
	    put("gold",0xE8DA10);
	    put("diamond",0x1BDEBD);
		put("starsteel",0x1C1C1C);
		put("pyrite",0xC4B012);
	}};

}
