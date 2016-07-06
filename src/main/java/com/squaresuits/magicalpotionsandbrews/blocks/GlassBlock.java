package com.squaresuits.magicalpotionsandbrews.blocks;

import com.squaresuits.magicalpotionsandbrews.material.ResourceMaterial;
import com.squaresuits.magicalpotionsandbrews.registry.MPBOreDictionaryEntry;

import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class GlassBlock extends BlockGlass implements MPBOreDictionaryEntry{

	protected final ResourceMaterial metal;
	private final String oreDict;
	
	public GlassBlock(ResourceMaterial metal, boolean ignoreSimilarity) {
		super(Material.GLASS, ignoreSimilarity);
		this.fullBlock = true;
		//this.lightOpacity = 255;
		//this.translucent = true;
		this.metal = metal;
		this.blockSoundType = SoundType.GLASS;
		this.blockHardness = (float) 0.3;
		this.oreDict = "block"+metal.getCapitalizedName();
		//this.setHarvestLevel("pickaxe", metal.getRequiredHarvestLevel());
	}

	///// OVERRIDE OF ALL METHODS THAT DEPEND ON BLOCK MATERIAL: /////
	@Override
	public MapColor getMapColor(final IBlockState p_getMapColor_1_) {
        return MapColor.IRON;
    }
	
	

	@Override
	public String getOreDictionaryName() {
		return "blockGlass"+metal.getCapitalizedName();
	}
}
