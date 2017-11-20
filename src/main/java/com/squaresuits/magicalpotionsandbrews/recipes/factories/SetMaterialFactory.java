package com.squaresuits.magicalpotionsandbrews.recipes.factories;

import com.google.gson.JsonObject;
import com.squaresuits.magicalpotionsandbrews.recipes.PABShapedOreRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.JsonUtils;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class SetMaterialFactory implements IRecipeFactory{
    @Override
    public IRecipe parse(JsonContext context, JsonObject json) {
        ShapedOreRecipe recipe = ShapedOreRecipe.factory(context, json);

        ShapedPrimer primer = new ShapedPrimer();
        primer.width = recipe.getRecipeWidth();
        primer.height = recipe.getRecipeHeight();
        primer.mirrored = JsonUtils.getBoolean(json, "mirrored", true);
        primer.input = recipe.getIngredients();

        return new PABShapedOreRecipe(JsonUtils.getString(json,"material"), recipe.getRecipeOutput(), primer);
    }
}