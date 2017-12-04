package com.mcmoddev.alchemytrinkets.recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;


import com.mcmoddev.alchemytrinkets.items.ItemPotionFlask;
import com.mcmoddev.alchemytrinkets.init.Items;

import com.mcmoddev.alchemytrinkets.plugin.JEIAlchemyTrinketsPlugin;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;

public class FlaskRecipe extends ShapedOreRecipe{
	private String material = "";
	private String glass = "";

    public FlaskRecipe(ItemStack result, ShapedPrimer recipe){
        super(new ResourceLocation("mpab-shaped"), result, recipe);
        output.setTagCompound(result.getTagCompound());
    }
    
    @Override
    public ItemStack getCraftingResult(InventoryCrafting craftMatrix) {
    	//for(int i = 0; i < input.length; i++){
    		
    		//if(input[i] != null){
    			//ItemStack thestack = (ItemStack) input[i];

    			//Main.logger.info(i + ": " + thestack.getItem().getClass().getName());
    		//}
    	//}
    	ItemStack tmp = output.copy();

    	tmp.setStackDisplayName("Flask");
    	tmp.setTagCompound(new NBTTagCompound());
    	
    	tmp.getTagCompound().setString("flaskComponent", material);
    	tmp.getTagCompound().setString("infusedGlass", glass);
    	tmp.getTagCompound().setBoolean("isEmpty", true);
    	tmp.getTagCompound().setInteger("uses", 0);
    	tmp.getTagCompound().setInteger("maxUses", ((ItemPotionFlask) Items.potion_flask).flaskMaterialInfo.get(material)[ItemPotionFlask.MATUSE]);
    	
    	
    	//MPBItemPotionFlask  hi = (MPBItemPotionFlask) tmp.getItem();
    	//hi.setMaterials(input[1].toString());
    	return tmp;//new ItemStack(hi);
    }

    @Override
    public ItemStack getRecipeOutput(){

        /*System.out.println("testing outputs from default");
        System.out.println(input);
        System.out.println(input.get(1).getMatchingStacks());
        ItemStack out = output.copy();
        if(!output.hasTagCompound()){
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("flaskComponent", "copper");
            tag.setString("infusedGlass", "pyrite");
            tag.setBoolean("isEmpty", true);
            tag.setInteger("uses", 0);
            tag.setInteger("maxUses", 4);
            output.setTagCompound(tag);
        }*/
        return output;
    }
    @Nonnull
    public final List<List<ItemStack>> getInput() {
        IStackHelper stackHelper = JEIAlchemyTrinketsPlugin.helpers.getStackHelper();

        List<List<ItemStack>> inputs = stackHelper.expandRecipeItemStackInputs(this.getIngredients());
        return inputs;
    }
    @Nonnull
    public final ItemStack getOutput() {
        ItemStack out = output.copy();
            out.setTagCompound(null);
        return out;
    }
    
    @Override
    public boolean matches(InventoryCrafting inv, World world)
    {

        for (int x = 0; x <= inv.getWidth() - width; x++) {
            for (int y = 0; y <= inv.getHeight() - height; ++y) {
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
