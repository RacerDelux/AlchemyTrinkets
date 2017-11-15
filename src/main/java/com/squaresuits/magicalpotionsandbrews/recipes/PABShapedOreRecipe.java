package com.squaresuits.magicalpotionsandbrews.recipes;

import com.squaresuits.magicalpotionsandbrews.init.Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class PABShapedOreRecipe extends ShapedOreRecipe{
	private String material = "";

    public PABShapedOreRecipe(String mat, Object... recipe){
        super(null, Items.flask_component, recipe);

    	material = mat;
    	NBTTagCompound outputdata = new NBTTagCompound();
    	outputdata.setString("material", material);
    	output.setTagCompound(outputdata);
    	 }
    


}
