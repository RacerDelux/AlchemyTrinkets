package com.squaresuits.magicalpotionsandbrews.blocks;

import java.util.Random;

import com.squaresuits.magicalpotionsandbrews.init.MPBBlocks;
import com.squaresuits.magicalpotionsandbrews.init.MPBItems;
import com.squaresuits.magicalpotionsandbrews.material.MPBResourceMaterial;
import com.squaresuits.magicalpotionsandbrews.registry.MPBOreDictionaryEntry;

import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class MPBBlockOre extends BlockOre implements MPBOreDictionaryEntry {
	
	protected final MPBResourceMaterial metal;
	private final String oreDict;
	
	public MPBBlockOre(MPBResourceMaterial metal) {
		//super();
		this.setSoundType(SoundType.STONE);
		this.metal = metal;
		this.blockHardness = Math.max(5f,metal.getOreBlockHardness());
		this.blockResistance = Math.max(1.5f,metal.getBlastResistance()*0.75f);
		this.setHarvestLevel("pickaxe", metal.getRequiredHarvestLevel());
		this.oreDict = "ore"+metal.getCapitalizedName();
		//this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	@Override
    public int getExpDrop(final IBlockState bs, IBlockAccess w, final BlockPos coord, final int i) {
        return 0; // xp comes from smelting
    }
	
	@Override
	public String getOreDictionaryName() {
		return oreDict;
	}
	public MPBResourceMaterial getMetalMaterial(){
		return metal;
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return this == MPBBlocks.topaz_ore ? MPBItems.topaz_stone : Item.getItemFromBlock(this);
	}
	
	public int quantityDropped(Random random) {
		return this == MPBBlocks.pyrite_ore ? 3 + random.nextInt(3) : 1;
	}
	
	public MPBResourceMaterial getMetal(){
		return metal;
	}
}
