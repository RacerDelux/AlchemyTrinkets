package com.squaresuits.magicalpotionsandbrews.items;

import com.squaresuits.magicalpotionsandbrews.material.MPBResourceMaterial;

import net.minecraft.item.Item;

public class MPBItemShard extends Item {

	protected final MPBResourceMaterial metal;
	public MPBItemShard(MPBResourceMaterial metal) {
		//super();
		this.metal = metal;
		//this.setCreativeTab(CreativeTabs.MATERIALS);
	}
}
