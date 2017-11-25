package com.mcmoddev.alchemytrinkets.items;

import com.mcmoddev.alchemytrinkets.material.ResourceMaterial;

import net.minecraft.item.Item;

public class ItemInfusedGlass extends Item {
protected final ResourceMaterial metal;
	
	public ItemInfusedGlass(ResourceMaterial metal) {
		//super();
		this.metal = metal;

		//this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	public ResourceMaterial getMetalMaterial(){
		return metal;
	}
}
