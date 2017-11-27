package com.mcmoddev.alchemytrinkets.plugin;

import com.mcmoddev.alchemytrinkets.init.Items;
import com.mcmoddev.alchemytrinkets.plugin.recipeWrapper.FlaskRecipeMaker;
import com.mcmoddev.alchemytrinkets.recipes.FlaskRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;

import javax.annotation.Nonnull;

@JEIPlugin
public class JEIAlchemyTrinketsPlugin implements IModPlugin {
    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        System.out.println("registry attempted here");
        registry.addRecipes(FlaskRecipeMaker.getFlaskRecipies, "flasks");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
        subtypeRegistry.useNbtForSubtypes(Items.flask_component);
    }
}
