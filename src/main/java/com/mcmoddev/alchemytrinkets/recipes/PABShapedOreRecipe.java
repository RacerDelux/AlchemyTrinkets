package com.mcmoddev.alchemytrinkets.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class PABShapedOreRecipe extends ShapedOreRecipe{
	private String material = "";

    public PABShapedOreRecipe(String mat, ItemStack result, ShapedPrimer recipe){
        super(new ResourceLocation("mpab-shaped"), result, recipe);

    	material = mat;
    	NBTTagCompound outputdata = new NBTTagCompound();
    	outputdata.setString("material", material);
    	output.setTagCompound(outputdata);
    	 }
    


}
