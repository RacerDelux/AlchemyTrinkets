package com.squaresuits.magicalpotionsandbrews;

import com.squaresuits.magicalpotionsandbrews.init.MPBItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MPBCreativeTab extends CreativeTabs{

	public MPBCreativeTab(String string) {
		super(string);
		
	}
	
	public Item getTabIconItem() {
		return MPBItems.potion_flask;
	}

}
