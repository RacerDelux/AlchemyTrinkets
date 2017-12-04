package com.mcmoddev.alchemytrinkets.plugin;

import com.mcmoddev.alchemytrinkets.init.Items;
import com.mcmoddev.alchemytrinkets.plugin.recipeWrapper.FlaskRecipeWrapper;
import com.mcmoddev.alchemytrinkets.recipes.FlaskRecipe;
import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

import javax.annotation.Nonnull;

@JEIPlugin
public class JEIAlchemyTrinketsPlugin implements IModPlugin {
    public static IJeiHelpers helpers;

    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        helpers = registry.getJeiHelpers();
        System.out.println("registry attempted here");

        registry.handleRecipes(FlaskRecipe.class, new IRecipeWrapperFactory<FlaskRecipe>() {
            @Override
            public IRecipeWrapper getRecipeWrapper(FlaskRecipe recipe) {
                return new FlaskRecipeWrapper(recipe);
            }
        }, VanillaRecipeCategoryUid.CRAFTING);

        //registry.addRecipes(FlaskRecipeWrapper.getFlaskRecipies, "flasks");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
        subtypeRegistry.useNbtForSubtypes(Items.flask_component);
    }
}
