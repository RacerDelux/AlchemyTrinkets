package com.squaresuits.magicalpotionsandbrews.items.tools;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;

import net.minecraft.item.ItemPickaxe;

public class MPBCopperPickaxe extends ItemPickaxe{
	public MPBCopperPickaxe(String unlocalizedName, ToolMaterial material){
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(MPBGlobal.MyCrTab);
	}
}
