package com.squaresuits.magicalpotionsandbrews.blocks;

import java.util.Random;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.init.MPBBlocks;
import com.squaresuits.magicalpotionsandbrews.init.MPBItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class MPBBlock extends Block{
	public MPBBlock(String unlocalizedName, Material material, float hardness, float resistance){
		super(material);
		
		this.setCreativeTab(MPBGlobal.MyCrTab);
		this.setUnlocalizedName(unlocalizedName);
		this.setHardness(hardness);
		this.setResistance(resistance);
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return this == MPBBlocks.topazOre ? MPBItems.topaz_stone : 
			   this == MPBBlocks.pyriteOre ? MPBItems.pyrite_shard : Item.getItemFromBlock(this);
	}
	
	public int quantityDropped(Random random) {
		return this == MPBBlocks.pyriteOre ? 3 + random.nextInt(3) : 1;
	}
}
