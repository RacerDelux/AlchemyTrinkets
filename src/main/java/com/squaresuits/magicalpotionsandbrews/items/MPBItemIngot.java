package com.squaresuits.magicalpotionsandbrews.items;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.material.MPBResourceMaterial;

import net.minecraft.item.Item;

public class MPBItemIngot extends Item {
	protected final MPBResourceMaterial metal;
	public MPBItemIngot(MPBResourceMaterial metal) {
		//super();
		this.metal = metal;
		//this.setCreativeTab(CreativeTabs.MATERIALS);
	}
}
