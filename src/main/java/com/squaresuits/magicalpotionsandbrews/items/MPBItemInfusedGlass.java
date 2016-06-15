package com.squaresuits.magicalpotionsandbrews.items;

import com.squaresuits.magicalpotionsandbrews.material.MPBResourceMaterial;

import net.minecraft.item.Item;

public class MPBItemInfusedGlass extends Item {
protected final MPBResourceMaterial metal;
	
	public MPBItemInfusedGlass(MPBResourceMaterial metal) {
		//super();
		this.metal = metal;

		//this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	public MPBResourceMaterial getMetalMaterial(){
		return metal;
	}
}
