package com.squaresuits.magicalpotionsandbrews.items;

import com.squaresuits.magicalpotionsandbrews.material.MPBResourceMaterial;

import net.minecraft.item.Item;

public class MPBItemGem extends Item {
	protected final MPBResourceMaterial metal;
	public MPBItemGem(MPBResourceMaterial metal) {
		//super();
		this.metal = metal;
		//this.setCreativeTab(CreativeTabs.MATERIALS);
	}
}
