package com.squaresuits.magicalpotionsandbrews.items;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MPBItem extends Item{
	public MPBItem(String name) {
		super();
		
		this.setUnlocalizedName(name);
		this.setCreativeTab(MPBGlobal.MyCrTab);
	}
}
