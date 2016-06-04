package com.squaresuits.magicalpotionsandbrews.items;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.material.MPBResourceMaterial;
import com.squaresuits.magicalpotionsandbrews.registry.MPBOreDictionaryEntry;

import net.minecraft.item.Item;

public class MPBItemIngot extends Item implements MPBOreDictionaryEntry{
	protected final MPBResourceMaterial metal;
	private final String oreDict;
	
	public MPBItemIngot(MPBResourceMaterial metal) {
		//super();
		this.metal = metal;
		this.oreDict = "ingot"+metal.getCapitalizedName();
		//this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	@Override
	public String getOreDictionaryName() {
		return oreDict;
	}
	public MPBResourceMaterial getMetalMaterial(){
		return metal;
	}
}
