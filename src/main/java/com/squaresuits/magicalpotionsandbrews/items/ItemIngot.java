package com.squaresuits.magicalpotionsandbrews.items;

import com.squaresuits.magicalpotionsandbrews.material.ResourceMaterial;
import com.squaresuits.magicalpotionsandbrews.recipes.MPBOreDictionaryEntry;

import net.minecraft.item.Item;

public class ItemIngot extends Item implements MPBOreDictionaryEntry{
	protected final ResourceMaterial metal;
	private final String oreDict;
	
	public ItemIngot(ResourceMaterial metal) {
		//super();
		this.metal = metal;
		this.oreDict = "ingot"+metal.getCapitalizedName();
		//this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	@Override
	public String getOreDictionaryName() {
		return oreDict;
	}
	public ResourceMaterial getMetalMaterial(){
		return metal;
	}
}
