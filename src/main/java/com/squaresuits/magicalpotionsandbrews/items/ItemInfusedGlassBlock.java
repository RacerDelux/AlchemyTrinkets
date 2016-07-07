package com.squaresuits.magicalpotionsandbrews.items;

import java.util.regex.Pattern;

import com.squaresuits.magicalpotionsandbrews.Main;
import com.squaresuits.magicalpotionsandbrews.blocks.BlockInfusedGlass;
import com.squaresuits.magicalpotionsandbrews.util.FlaskUtil;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * User: The Grey Ghost
 * Date: 27/12/2014
 * We need a custom item to represent the different sub-types (colours) of BlockVariants.
 * The Itemstack metadata represents the subtype.
 * You could also re-use ItemMultiTexture or ItemCloth or ItemColored.
 * Look at Item.registerItems() for inspiration
 */
public class ItemInfusedGlassBlock extends ItemBlock
{
  // you must use Block in the constructor, not BlockVariants, otherwise you won't be able to register the block properly.
  //   i.e. using GameRegistry.registerBlock(block, ItemBlockVariants.class, name)
  public ItemInfusedGlassBlock(Block block)
  {
    super(block);
    this.setMaxDamage(0);
    this.setHasSubtypes(true);
  }

  @Override
  public int getMetadata(int metadata)
  {
    return metadata;
  }

  // create a unique unlocalised name for each colour, so that we can give each one a unique name
  @Override
  public String getUnlocalizedName(ItemStack stack)
  {
    //BlockVariants.EnumColour colour = BlockVariants.EnumColour.byMetadata(stack.getMetadata());
    //return super.getUnlocalizedName() + "." + colour.toString();
    //return setName( super.getUnlocalizedName(), FlaskUtil.getNameFromMeta(stack.getMetadata()));
    BlockInfusedGlass.EnumMat material = BlockInfusedGlass.EnumMat.byMetadata(stack.getMetadata());
    return setName(super.getUnlocalizedName() , material.toString());
  }
  
  private String setName(String input, String material){
  	String[] base = input.split(Pattern.quote("."), 3);
  	String first = base[0];
  	String second = base[1];
  	String third = base[2];
  	return first + "." + second + "." + material + "_" + third;
  }
  
}
