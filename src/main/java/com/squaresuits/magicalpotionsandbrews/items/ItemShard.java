package com.squaresuits.magicalpotionsandbrews.items;

import com.squaresuits.magicalpotionsandbrews.material.ResourceMaterial;
import com.squaresuits.magicalpotionsandbrews.registry.MPBOreDictionaryEntry;

import net.minecraft.item.Item;

public class ItemShard extends Item implements MPBOreDictionaryEntry {

	protected final ResourceMaterial metal;
	private final String oreDict;
	
	public ItemShard(ResourceMaterial metal) {
		//super();
		this.metal = metal;
		this.oreDict = "shard"+metal.getCapitalizedName();
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
	
	public ResourceMaterial getMetal(){
		return metal;
	}
}
