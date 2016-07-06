package com.squaresuits.magicalpotionsandbrews.crafting;

import com.squaresuits.magicalpotionsandbrews.init.Blocks;
import com.squaresuits.magicalpotionsandbrews.init.Items;
import com.squaresuits.magicalpotionsandbrews.registry.FlaskRecipe;
import com.squaresuits.magicalpotionsandbrews.registry.PABShapedOreRecipe;
import com.squaresuits.magicalpotionsandbrews.registry.PotionAdditionFlaskRecipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import scala.Console;

public class Recipes {
	
	public static void initRecipes(){
		
		//Potion add to flask
		GameRegistry.addRecipe(new PotionAdditionFlaskRecipe(new ItemStack(Items.potion_flask),
				new Object [] {
						"xo ",
						"   ",
						"   ",
						'o', new ItemStack(net.minecraft.init.Items.POTIONITEM,1,OreDictionary.WILDCARD_VALUE),
						'x', Items.potion_flask
				}));
		//Pyrite Block
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.pyrite_block),
				new Object [] {
						"###",
						"###",
						"###",
						'#', "ingotPyrite"
				}));
		//Pyrite Infused Glass
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.pyrite_glass_block, 4),
				new Object [] {
						"###",
						"#@#",
						"###",
						'#', "blockGlass",
						'@', "ingotPyrite"
				}));
		//Pyrite Ingot
		GameRegistry.addShapelessRecipe(new ItemStack(Items.pyrite_ingot, 9),
				new Object [] {
				Blocks.pyrite_block
				});
		// Flask Component
    	GameRegistry.addRecipe(new PABShapedOreRecipe(new ItemStack(Items.flask_component), "iron",
				new Object [] {
						" o ",
						"oxo",
						"   ",
						'o', "ingotIron",
						'x', "ingotPyrite"
				}));
    	GameRegistry.addRecipe(new PABShapedOreRecipe(new ItemStack(Items.flask_component), "gold",
				new Object [] {
						" o ",
						"oxo",
						"   ",
						'o', "ingotGold",
						'x', "ingotPyrite"
				}));
    	GameRegistry.addRecipe(new PABShapedOreRecipe(new ItemStack(Items.flask_component), "diamond",
				new Object [] {
						" o ",
						"oxo",
						"   ",
						'o', "gemDiamond",
						'x', "ingotPyrite"
				}));
    	
    	//Potion Flask
    	GameRegistry.addRecipe(new FlaskRecipe(new ItemStack(Items.potion_flask),
				new Object [] {
						" o ",
						"x x",
						"xxx",
						'o', new ItemStack(Items.flask_component,1,OreDictionary.WILDCARD_VALUE),
						'x', Blocks.pyrite_glass_block
				}));
		 if (Loader.isModLoaded("basemetals")) {
	            try {
	            	//Copper Flask Component
	            	GameRegistry.addRecipe(new PABShapedOreRecipe(new ItemStack(Items.flask_component), "copper",
	        				new Object [] {
	        						" o ",
	        						"oxo",
	        						"   ",
	        						'o', "ingotCopper",
	        						'x', "ingotPyrite"
	        				}));
	            	//starsteel Flask Component
	            	GameRegistry.addRecipe(new PABShapedOreRecipe(new ItemStack(Items.flask_component), "starsteel",
	        				new Object [] {
	        						" o ",
	        						"oxo",
	        						"   ",
	        						'o', "ingotStarsteel",
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
