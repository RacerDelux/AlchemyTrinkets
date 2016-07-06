package com.squaresuits.magicalpotionsandbrews.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class PABShapedOreRecipe extends ShapedOreRecipe{
	private String material = "";
	public PABShapedOreRecipe(Block     result, Object... recipe){super(result, recipe); }
    public PABShapedOreRecipe(Item      result, Object... recipe){ super(result, recipe); }
    public PABShapedOreRecipe(ItemStack result, Object... recipe){ 
    	super(result, recipe); }
    public PABShapedOreRecipe(ItemStack result, String mat, Object... recipe){ 
    	this(result, recipe);
    	material = mat;
    	NBTTagCompound outputdata = new NBTTagCompound();
    	outputdata.setString("material", material);
    	output.setTagCompound(outputdata);
    	 }
    


}
