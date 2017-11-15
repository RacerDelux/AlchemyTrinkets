package com.squaresuits.magicalpotionsandbrews;

import com.squaresuits.magicalpotionsandbrews.init.Items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MPBCreativeTab extends CreativeTabs{

	public MPBCreativeTab(String string) {
		super(string);
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.potion_mock);
	}

}
