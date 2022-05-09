package com.mcmoddev.alchemytrinkets.init;

import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.material.MMDMaterialType;

public abstract class Materials extends com.mcmoddev.lib.init.Materials{
	private static boolean initDone = false;

	public static void init(){
		if( initDone ) return;

		createMaterial("pyrite", MMDMaterialType.MaterialType.METAL, 3.5, 3, 9, 0x996868FF);
		/*createMaterial("copper", MMDMaterial.MaterialType.METAL, 3.5, 3, 9, 0xFFFF9F78);
		createMaterial("nickel", MMDMaterial.MaterialType.METAL, 3.5, 3, 9, 0xFFEEFFEB);*/
		createMaterial("topaz", MMDMaterialType.MaterialType.METAL, 3.5, 3, 9, 0x0000FFFF);

		createMaterial("potion", MMDMaterialType.MaterialType.CRYSTAL, 5, 3, 10, 0x996868FF);

		initDone = true;
	}

}

