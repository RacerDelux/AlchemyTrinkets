package com.squaresuits.magicalpotionsandbrews.items;

import com.mcmoddev.lib.material.MMDMaterial;
import com.squaresuits.magicalpotionsandbrews.recipes.MPBOreDictionaryEntry;

import net.minecraft.item.Item;

public class ItemGem extends Item  implements MPBOreDictionaryEntry {
	protected final MMDMaterial material;
	private final String oreDict;
	
	public ItemGem(MMDMaterial material) {
		//super();
		this.material = material;
		this.oreDict = "gem"+material.getCapitalizedName();
		//this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	@Override
	public String getOreDictionaryName() {
		// TODO Auto-generated method stub
		return oreDict;
	}
	public MMDMaterial getMetalMaterial(){
		return material;
	}
}
