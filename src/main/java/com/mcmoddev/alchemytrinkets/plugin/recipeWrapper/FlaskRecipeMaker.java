package com.mcmoddev.alchemytrinkets.plugin.recipeWrapper;

import com.google.common.collect.ImmutableList;
import com.mcmoddev.alchemytrinkets.recipes.FlaskRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class FlaskRecipeMaker {
    public static List<FabricatorRecipeWrapper> getFlaskRecipies() {
        List<FabricatorRecipeWrapper> recipes = new ArrayList<>();
        for (IFabricatorRecipe recipe : RecipeManagers.fabricatorManager.recipes()) {
            recipes.add(new FabricatorRecipeWrapper(recipe));
        }
        return recipes;
    }
}
