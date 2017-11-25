package com.mcmoddev.alchemytrinkets;

import com.mcmoddev.alchemytrinkets.init.Items;

import net.minecraft.creativetab.CreativeTabs;
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
