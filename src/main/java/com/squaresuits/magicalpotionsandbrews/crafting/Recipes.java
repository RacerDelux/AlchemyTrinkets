package com.squaresuits.magicalpotionsandbrews.crafting;

import com.squaresuits.magicalpotionsandbrews.init.Blocks;
import com.squaresuits.magicalpotionsandbrews.init.Items;
import com.squaresuits.magicalpotionsandbrews.registry.FlaskRecipe;
import com.squaresuits.magicalpotionsandbrews.registry.PABShapedOreRecipe;
import com.squaresuits.magicalpotionsandbrews.registry.PotionAdditionFlaskRecipe;
import com.squaresuits.magicalpotionsandbrews.util.FlaskUtil;

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
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.infused_glass_block, 4, 0),
				new Object [] {
						"###",
						"#@#",
						"###",
						'#', "blockGlass",
						'@', "ingotPyrite"
		}));
		//Diamond Infused Glass
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.infused_glass_block, 4, 1),
						new Object [] {
								"#%#",
								"#@#",
								"#%#",
								'#', "blockGlass",
								'@', "ingotPyrite",
								'%', "gemDiamond"
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

		//Potion Flask
		GameRegistry.addRecipe(new FlaskRecipe(new ItemStack(Items.potion_flask),
				new Object [] {
						" o ",
						"x x",
						"xxx",
						'o', new ItemStack(Items.flask_component,1,OreDictionary.WILDCARD_VALUE),
						'x', new ItemStack(Blocks.infused_glass_block,1,OreDictionary.WILDCARD_VALUE)
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
				FlaskUtil.flaskMaterials.add("copper");
				//starsteel Flask Component
				GameRegistry.addRecipe(new PABShapedOreRecipe(new ItemStack(Items.flask_component), "starsteel",
						new Object [] {
								" o ",
								"oxo",
								"   ",
								'o', "ingotStarsteel",
								'x', "ingotPyrite"
				}));
				FlaskUtil.flaskMaterials.add("starsteel");
				Console.out().println("Base Metals found - recipes added!");
			}
			catch (Exception e) {
				Console.out().println("Base Metals not found!");
				e.printStackTrace(System.err);
			}
		}
		if (Loader.isModLoaded("fyrestone")) {
			try {
				//Fyrestone Flask Component
				GameRegistry.addRecipe(new PABShapedOreRecipe(new ItemStack(Items.flask_component), "copper",
						new Object [] {
								" o ",
								"oxo",
								"   ",
								'o', "ingotFyrestone",
								'x', "ingotPyrite"
				}));
				FlaskUtil.flaskMaterials.add("fyrestone");
				//Earthstone Flask Component
				GameRegistry.addRecipe(new PABShapedOreRecipe(new ItemStack(Items.flask_component), "starsteel",
						new Object [] {
								" o ",
								"oxo",
								"   ",
								'o', "ingotEarthstone",
								'x', "ingotPyrite"
				}));
				FlaskUtil.flaskMaterials.add("earthstone");
				Console.out().println("Fyrestone found - recipes added!");
			}
			catch (Exception e) {
				Console.out().println("Base Metals not found!");
				e.printStackTrace(System.err);
			}
		}
	}

}
