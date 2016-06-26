package com.squaresuits.magicalpotionsandbrews.util;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IColorItem {
	@SideOnly(Side.CLIENT)
    IItemColor getColor();
}
