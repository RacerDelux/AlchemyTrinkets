package com.squaresuits.magicalpotionsandbrews.recipes;

import java.util.regex.Pattern;

import com.squaresuits.magicalpotionsandbrews.Main;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class PotionAdditionFlaskRecipe extends ShapedOreRecipe{
	
	private NBTTagCompound currenttag = new NBTTagCompound();
	
	//public PotionAdditionFlaskRecipe(Block     result, Object... recipe){super(result, recipe); }
    //public PotionAdditionFlaskRecipe(Item      result, Object... recipe){ super(result, recipe); }
    public PotionAdditionFlaskRecipe(ItemStack result, Object... recipe){ 
    	super(null, result, recipe); }
    
    @Override
    public ItemStack getCraftingResult(InventoryCrafting craftMatrix) {
    	/*for(int i = 0; i < input.length; i++){
    		
    		if(input[i] != null){
    			ItemStack thestack = (ItemStack) input[i];
    			
    			Main.logger.info(i + ": " + thestack.getItem().getClass().getName());
    		}
    	}
    	ItemStack newStack = output.copy();
    	

    	newStack.setTagCompound(currenttag);

    	return newStack;*/
    	return null;
    }
    
    @Override
    public boolean matches(InventoryCrafting inv, World world)
    {

        for (int x = 0; x <= MAX_CRAFT_GRID_WIDTH - width; x++)
        {
            for (int y = 0; y <= MAX_CRAFT_GRID_HEIGHT - height; ++y)
            {
                if (checkMatch(inv, x, y, false))
                {
                    return customMatches(inv);
                }

                if (mirrored && checkMatch(inv, x, y, true))
                {
                    return customMatches(inv);
                }
            }
        }

        return false;
    }

	private boolean customMatches(InventoryCrafting inv){
		currenttag = new NBTTagCompound();
		ItemStack flask = inv.getStackInSlot(0);

		ItemStack potion = inv.getStackInSlot(1);
		if(flask != null && potion != null){
			if(flask.getTagCompound() != null){
				currenttag = flask.getTagCompound().copy();
				/*currenttag.setString("flaskComponent", flask.getTagCompound().getString("flaskComponent"));
				currenttag.setString("infusedGlass", flask.getTagCompound().getString("infusedGlass"));
				currenttag.setBoolean("isEmpty", flask.getTagCompound().getBoolean("isEmpty"));
				currenttag.setInteger("uses", flask.getTagCompound().getInteger("uses"));
				currenttag.setInteger("maxUses", flask.getTagCompound().getInteger("maxUses"));
				Main.logger.info("Flask has NBT: " + flask.getTagCompound());*/
			}
			if(potion.getTagCompound() != null){
				if(!currenttag.getBoolean("isEmpty")){
					if(currenttag.getInteger("uses") == currenttag.getInteger("maxUses")
							|| !flask.getTagCompound().getString("Potion").equals(potion.getTagCompound().getString("Potion"))){
						return false;
					}
					currenttag.setString("Potion", flask.getTagCompound().getString("Potion"));
					currenttag.setInteger("uses", currenttag.getInteger("uses") + 1);
				} else {
					currenttag.setString("Potion", potion.getTagCompound().getString("Potion"));
					currenttag.setBoolean("isEmpty", false);
					currenttag.setInteger("uses", 1);
				}
				Main.logger.info("Potion has NBT: " + potion.getTagCompound());
			}
		}
		return true;
	}
    private String getNameOfItem(String str){
    	return str.split(Pattern.quote("."), 3)[2].split("_", 2)[0];
    }
}
