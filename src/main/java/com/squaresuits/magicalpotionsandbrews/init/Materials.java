package com.squaresuits.magicalpotionsandbrews.init;

import com.mcmoddev.lib.material.MMDMaterial;

public abstract class Materials extends com.mcmoddev.lib.init.Materials{
	private static boolean initDone = false;
	
	public static void init(){
		if( initDone ) return;
		
		createMaterial("pyrite", MMDMaterial.MaterialType.METAL, 3.5, 3, 9, 0x996868FF);
		/*createMaterial("copper", MMDMaterial.MaterialType.METAL, 3.5, 3, 9, 0xFFFF9F78);
		createMaterial("nickel", MMDMaterial.MaterialType.METAL, 3.5, 3, 9, 0xFFEEFFEB);*/
		createMaterial("topaz", MMDMaterial.MaterialType.METAL, 3.5, 3, 9, 0x0000FFFF);

		createMaterial("potion", MMDMaterial.MaterialType.CRYSTAL, 5, 3, 10, 0x996868FF);
		
		initDone = true;
	}
	
}
