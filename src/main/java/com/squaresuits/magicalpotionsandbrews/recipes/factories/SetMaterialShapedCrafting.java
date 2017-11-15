package com.squaresuits.magicalpotionsandbrews.recipes.factories;

import com.google.gson.JsonObject;
import com.squaresuits.magicalpotionsandbrews.recipes.PABShapedOreRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;

public class SetMaterialShapedCrafting implements IRecipeFactory{
    @Override
    public IRecipe parse(JsonContext context, JsonObject json) {
        return new PABShapedOreRecipe(JsonUtils.getString(json,"material"));
    }
}