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

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class FlaskRecipe extends ShapedOreRecipe{
	public static HashMap<String, Integer> materialUses = new HashMap<String, Integer>(){{
		put("copper",4);
		put("iron",5);
	    put("gold",10);
	    put("diamond",15);
		put("starsteel",25);
	}};
	public static HashMap<String, Integer> materialColor = new HashMap<String, Integer>(){{
		put("copper",0xEDA726);
	    put("iron",0xB0B0B0);
	    put("gold",0xE8DA10);
	    put("diamond",0x1BDEBD);
		put("starsteel",0x1C1C1C);
		put("pyrite",0xC4B012);
	}};
	public FlaskRecipe(Block     result, Object... recipe){super(result, recipe); }
    public FlaskRecipe(Item      result, Object... recipe){ super(result, recipe); }
    public FlaskRecipe(ItemStack result, Object... recipe){ 
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
    	ItemStack flaskComponentUsed = ((ItemStack) input[1]);
    	ItemStack infusedGlassUsed = ((ItemStack) input[3]);
    	//NBTTagCompound hi = null;
    	
    	tmp.setStackDisplayName("Flask");
    	tmp.setTagCompound(new NBTTagCompound());
    	

    	tmp.getTagCompound().setString("flaskComponent", getNameOfItem(flaskComponentUsed.getUnlocalizedName()));
    	tmp.getTagCompound().setString("infusedGlass", getNameOfItem(infusedGlassUsed.getUnlocalizedName()));
    	tmp.getTagCompound().setBoolean("isEmpty", true);
    	tmp.getTagCompound().setInteger("uses", 0);
    	tmp.getTagCompound().setInteger("maxUses", materialUses.get(tmp.getTagCompound().getString("flaskComponent")));
    	
    	//MPBItemPotionFlask  hi = (MPBItemPotionFlask) tmp.getItem();
    	//hi.setMaterials(input[1].toString());
    	return tmp;//new ItemStack(hi);
    }
    
    private String getNameOfItem(String str){
    	return str.split(Pattern.quote("."), 3)[2].split("_", 2)[0];
    }
}
