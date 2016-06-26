package com.squaresuits.magicalpotionsandbrews.items;

import com.squaresuits.magicalpotionsandbrews.material.ResourceMaterial;
import com.squaresuits.magicalpotionsandbrews.registry.MPBOreDictionaryEntry;

import net.minecraft.item.Item;

public class ItemGem extends Item  implements MPBOreDictionaryEntry {
	protected final ResourceMaterial metal;
	private final String oreDict;
	
	public ItemGem(ResourceMaterial metal) {
		//super();
		this.metal = metal;
		this.oreDict = "gem"+metal.getCapitalizedName();
		//this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	@Override
	public String getOreDictionaryName() {
		// TODO Auto-generated method stub
		return oreDict;
	}
	public ResourceMaterial getMetalMaterial(){
		return metal;
	}
}
