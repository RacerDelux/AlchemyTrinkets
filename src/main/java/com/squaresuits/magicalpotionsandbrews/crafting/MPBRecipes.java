package com.squaresuits.magicalpotionsandbrews.crafting;

import com.squaresuits.magicalpotionsandbrews.init.MPBBlocks;
import com.squaresuits.magicalpotionsandbrews.init.MPBItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MPBRecipes {
	
	public static void initRecipes(){
		
		GameRegistry.addRecipe(new ItemStack(MPBBlocks.pyriteBlock),
				new Object [] {
						"###",
						"###",
						"###",
						'#', MPBItems.pyrite_ingot
				});
		GameRegistry.addShapelessRecipe(new ItemStack(MPBItems.pyrite_ingot, 9),
				new Object [] {
				MPBBlocks.pyriteBlock
				});
	}

}
