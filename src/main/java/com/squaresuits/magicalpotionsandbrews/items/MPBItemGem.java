package com.squaresuits.magicalpotionsandbrews.items;

import com.squaresuits.magicalpotionsandbrews.material.MPBResourceMaterial;
import com.squaresuits.magicalpotionsandbrews.registry.MPBOreDictionaryEntry;

import net.minecraft.item.Item;

public class MPBItemGem extends Item  implements MPBOreDictionaryEntry {
	protected final MPBResourceMaterial metal;
	private final String oreDict;
	
	public MPBItemGem(MPBResourceMaterial metal) {
		//super();
		this.metal = metal;
		this.oreDict = "ingot"+metal.getCapitalizedName();
		//this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	@Override
	public String getOreDictionaryName() {
		// TODO Auto-generated method stub
		return null;
	}
}
