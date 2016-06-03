package com.squaresuits.magicalpotionsandbrews.crafting;

import com.squaresuits.magicalpotionsandbrews.init.MPBBlocks;
import com.squaresuits.magicalpotionsandbrews.init.MPBItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import scala.Console;

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
		 if (Loader.isModLoaded("basemetals")) {
	            try {
	            	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MPBBlocks.copperOre),
	        				new Object [] {
	        						" # ",
	        						" # ",
	        						" # ",
	        						'#', "ingotCopper"
	        				}));
	                Console.out().println("Base Metals found - recipes added!");
	            }
	            catch (Exception e) {
	            	Console.out().println("Base Metals not found!");
	                e.printStackTrace(System.err);
	            }
	        }
	}

}
