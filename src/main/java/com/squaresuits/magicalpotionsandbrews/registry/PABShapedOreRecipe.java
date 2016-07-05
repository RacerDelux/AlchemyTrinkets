package com.squaresuits.magicalpotionsandbrews.registry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.Main;
import com.squaresuits.magicalpotionsandbrews.blocks.BlockBlock;
import com.squaresuits.magicalpotionsandbrews.items.ItemFlaskComponent;
import com.squaresuits.magicalpotionsandbrews.items.ItemInfusedGlass;
import com.squaresuits.magicalpotionsandbrews.items.ItemPotionFlask;
import com.squaresuits.magicalpotionsandbrews.util.flaskUtil;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.oredict.OreDictionary;
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
