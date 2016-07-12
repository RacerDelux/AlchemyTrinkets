package com.squaresuits.magicalpotionsandbrews.registry;

import java.util.regex.Pattern;


import com.squaresuits.magicalpotionsandbrews.init.Items;
import com.squaresuits.magicalpotionsandbrews.items.ItemPotionFlask;
import static com.squaresuits.magicalpotionsandbrews.items.ItemPotionFlask.MATUSE;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class FlaskRecipe extends ShapedOreRecipe{
	private String material = "";
	private String glass = "";
	public FlaskRecipe(Block     result, Object... recipe){super(result, recipe); }
    public FlaskRecipe(Item      result, Object... recipe){ super(result, recipe); }
    public FlaskRecipe(ItemStack result, Object... recipe){ super(result, recipe); }
    
    @Override
    public ItemStack getCraftingResult(InventoryCrafting craftMatrix) {
    	for(int i = 0; i < input.length; i++){
    		
    		if(input[i] != null){
    			ItemStack thestack = (ItemStack) input[i];
    			
    			//Main.logger.info(i + ": " + thestack.getItem().getClass().getName());
    		}
    	}
    	ItemStack tmp = output.copy();
    	ItemStack flaskComponentUsed = ((ItemStack) input[1]);
    	ItemStack infusedGlassUsed = ((ItemStack) input[3]);
    	//NBTTagCompound hi = null;
    	
    	tmp.setStackDisplayName("Flask");
    	tmp.setTagCompound(new NBTTagCompound());
    	
    	tmp.getTagCompound().setString("flaskComponent", material);
    	tmp.getTagCompound().setString("infusedGlass", glass);
    	tmp.getTagCompound().setBoolean("isEmpty", true);
    	tmp.getTagCompound().setInteger("uses", 0);
    	tmp.getTagCompound().setInteger("maxUses", ((ItemPotionFlask) Items.potion_flask).flaskMaterialInfo.get(material)[MATUSE]);
    	
    	
    	//MPBItemPotionFlask  hi = (MPBItemPotionFlask) tmp.getItem();
    	//hi.setMaterials(input[1].toString());
    	return tmp;//new ItemStack(hi);
    }
    
    
    @Override
    public boolean matches(InventoryCrafting inv, World world)
    {

        for (int x = 0; x <= MAX_CRAFT_GRID_WIDTH - width; x++) {
            for (int y = 0; y <= MAX_CRAFT_GRID_HEIGHT - height; ++y) {
                if (checkMatch(inv, x, y, false)) {
                    customMatched(inv);
                    return true;
                }

                if (mirrored && checkMatch(inv, x, y, true)) {
                    customMatched(inv);
                    return true;
                }
            }
        }

        return false;
    }
    private void customMatched(InventoryCrafting inv){
        ItemStack component = inv.getStackInSlot(1);
        if (component != null && component.hasTagCompound()) {
            material = component.getTagCompound().getString("material");
        }
        ItemStack glassUsed = inv.getStackInSlot(3);
        if (glassUsed != null) {
            glass = getNameOfItem(glassUsed.getUnlocalizedName());
        }
    }
    private String getNameOfItem(String str){
    	return str.split(Pattern.quote("."), 3)[2].split("_", 2)[0];
 
    }
}
