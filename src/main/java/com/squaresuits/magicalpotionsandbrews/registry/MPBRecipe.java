package com.squaresuits.magicalpotionsandbrews.registry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.Main;
import com.squaresuits.magicalpotionsandbrews.items.MPBItemFlaskComponent;
import com.squaresuits.magicalpotionsandbrews.items.MPBItemInfusedGlass;
import com.squaresuits.magicalpotionsandbrews.items.MPBItemPotionFlask;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class MPBRecipe extends ShapedOreRecipe{
	
	public MPBRecipe(Block     result, Object... recipe){super(result, recipe); }
    public MPBRecipe(Item      result, Object... recipe){ super(result, recipe); }
    public MPBRecipe(ItemStack result, Object... recipe){ 
    	super(result, recipe); }
    
    @Override
    public ItemStack getCraftingResult(InventoryCrafting craftMatrix) {
    	for(int i = 0; i < input.length; i++){
    		
    		if(input[i] != null){
    			ItemStack thestack = (ItemStack) input[i];
    			
    			Main.logger.info(i + ": " + thestack.getItem().getClass().getName());
    		}
    	}
    	ItemStack tmp = output.copy();
    	MPBItemFlaskComponent flaskComponentUsed = (MPBItemFlaskComponent) ((ItemStack) input[1]).getItem();
    	MPBItemInfusedGlass infusedGlassUsed = (MPBItemInfusedGlass) ((ItemStack) input[3]).getItem();
    	//NBTTagCompound hi = null;
    	if(input[1] instanceof MPBItemFlaskComponent){
    		Main.logger.info("Ok! this is a flask component, which means I can get the NBT off it!");
    	}
    	tmp.setStackDisplayName("Flask");
    	tmp.setTagCompound(new NBTTagCompound());
    	

    	tmp.getTagCompound().setString("flaskComponent", getNameOfItem(flaskComponentUsed.getUnlocalizedName()));
    	tmp.getTagCompound().setString("infusedGlass", getNameOfItem(infusedGlassUsed.getUnlocalizedName()));
    	//MPBItemPotionFlask  hi = (MPBItemPotionFlask) tmp.getItem();
    	//hi.setMaterials(input[1].toString());
    	return tmp;//new ItemStack(hi);
    }
    
    private String getNameOfItem(String str){
    	return str.split(Pattern.quote("."), 3)[2].split("_", 2)[0];
    }
}
