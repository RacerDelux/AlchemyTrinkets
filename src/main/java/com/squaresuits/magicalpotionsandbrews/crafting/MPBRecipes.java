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
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MPBBlocks.pyrite_block),
				new Object [] {
						"###",
						"###",
						"###",
						'#', "ingotPyrite"
				}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MPBItems.pyrite_infused_glass, 4),
				new Object [] {
						"# #",
						" @ ",
						"# #",
						'#', "blockGlass",
						'@', "ingotPyrite"
				}));
		GameRegistry.addShapelessRecipe(new ItemStack(MPBItems.pyrite_ingot, 9),
				new Object [] {
				MPBBlocks.pyrite_block
				});
		 if (Loader.isModLoaded("basemetals")) {
	            try {
	            	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MPBItems.copper_flask_component),
	        				new Object [] {
	        						" o ",
	        						"oxo",
	        						"   ",
	        						'o', "ingotCopper",
	        						'x', "ingotPyrite"
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
