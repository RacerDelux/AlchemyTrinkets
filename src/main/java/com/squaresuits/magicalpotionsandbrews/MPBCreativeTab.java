package com.squaresuits.magicalpotionsandbrews;

import com.squaresuits.magicalpotionsandbrews.init.Items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MPBCreativeTab extends CreativeTabs{

	public MPBCreativeTab(String string) {
		super(string);
		
	}
	
	@Override
	public Item getTabIconItem() {
		return Items.potion_mock;
	}

}
