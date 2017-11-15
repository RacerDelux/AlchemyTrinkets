package com.squaresuits.magicalpotionsandbrews.crafting;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.init.Blocks;
import com.squaresuits.magicalpotionsandbrews.init.Items;
import com.squaresuits.magicalpotionsandbrews.items.ItemPotionFlask;
import com.squaresuits.magicalpotionsandbrews.registry.FlaskRecipe;
import com.squaresuits.magicalpotionsandbrews.registry.PABShapedOreRecipe;
import com.squaresuits.magicalpotionsandbrews.registry.PotionAdditionFlaskRecipe;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import scala.Console;

public class Recipes {

	public static void initRecipes(){
		RecipeSorter.register(MPBGlobal.MOD_ID + ":flaskShapedRecipe", FlaskRecipe.class, RecipeSorter.Category.SHAPED, "");
		RecipeSorter.register(MPBGlobal.MOD_ID + ":pabShapedRecipe", PABShapedOreRecipe.class, RecipeSorter.Category.SHAPED, "");
		RecipeSorter.register(MPBGlobal.MOD_ID + ":potionAdditionShaped", PotionAdditionFlaskRecipe.class, RecipeSorter.Category.SHAPED, "");

		//Potion Flask//
		ItemPotionFlask flask = (ItemPotionFlask) Items.potion_flask;
		//Potion add to flask
		GameRegistry.addRecipe(new PotionAdditionFlaskRecipe(new ItemStack(Items.potion_flask),
				"xo ",
				"   ",
				"   ",
				'o', new ItemStack(net.minecraft.init.Items.POTIONITEM,1,OreDictionary.WILDCARD_VALUE),
				'x', Items.potion_flask));
		//Pyrite Block

		//DONE
		/*GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.pyrite_block),
				"###",
				"###",
				"###",
				'#', "ingotPyrite"));*/
		//Pyrite Infused Glass
		/*GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.infused_glass_block, 4, 0),
				"###",
				"#@#",
				"###",
				'#', "blockGlass",
				'@', "ingotPyrite"));*/
		//Diamond Infused Glass
        /*GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.infused_glass_block, 4, 1),
                "#%#",
                "#@#",
                "#%#",
                '#', "blockGlass",
                '@', "ingotPyrite",
                '%', "gemDiamond"));*/
        //Emerald Infused Glass
        /*GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.infused_glass_block, 4, 2),
                "#%#",
                "#@#",
                "#%#",
                '#', "blockGlass",
                '@', "ingotPyrite",
                '%', "gemEmerald"));*/
		//Pyrite Ingot
		//GameRegistry.addShapelessRecipe(new ItemStack(Items.pyrite_ingot, 9),
		//		Blocks.pyrite_block);
		// Flask Component
		GameRegistry.addRecipe(new PABShapedOreRecipe(new ItemStack(Items.flask_component), "iron",
				" o ",
				"oxo",
				"   ",
				'o', "ingotIron",
				'x', "ingotPyrite"));
		GameRegistry.addRecipe(new PABShapedOreRecipe(new ItemStack(Items.flask_component), "gold",
				" o ",
				"oxo",
				"   ",
				'o', "ingotGold",
				'x', "ingotPyrite"));

		//Potion Flask
		GameRegistry.addRecipe(new FlaskRecipe(addDefaultNBT(new ItemStack(Items.potion_flask)),
				" o ",
				"x x",
				"xxx",
				'o', new ItemStack(Items.flask_component,1,OreDictionary.WILDCARD_VALUE),
				'x', new ItemStack(Blocks.infused_glass_block,1,OreDictionary.WILDCARD_VALUE)));
		if (Loader.isModLoaded("basemetals")) {
			try {
				//Copper Flask Component
				GameRegistry.addRecipe(new PABShapedOreRecipe(new ItemStack(Items.flask_component), "copper",
						" o ",
						"oxo",
						"   ",
						'o', "ingotCopper",
						'x', "ingotPyrite"));
				flask.flaskMaterials.add("copper");
				//starsteel Flask Component
				GameRegistry.addRecipe(new PABShapedOreRecipe(new ItemStack(Items.flask_component), "starsteel",
						" o ",
						"oxo",
						"   ",
						'o', "ingotStarsteel",
						'x', "ingotPyrite"));
				flask.flaskMaterials.add("starsteel");
				Console.out().println("Base Metals found - recipes added!");
			}
			catch (Exception e) {
				Console.out().println("Base Metals not found!");
				e.printStackTrace(System.err);
			}
		}
	}

	private static ItemStack addDefaultNBT(ItemStack itemStack) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("flaskComponent", "copper");
		tag.setString("infusedGlass", "pyrite");
		tag.setBoolean("isEmpty", true);
		tag.setInteger("uses", 0);
		tag.setInteger("maxUses", 4);
		itemStack.setTagCompound(tag);
		return itemStack;
	}

	private static ItemStack addNBT(ItemStack item, String material){
		NBTTagCompound mat = new NBTTagCompound();
		mat.setString("material", material);
		item.setTagCompound(mat);
		return item;
	}

}
