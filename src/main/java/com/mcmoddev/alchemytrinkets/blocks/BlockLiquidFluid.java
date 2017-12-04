package com.mcmoddev.alchemytrinkets.blocks;

import javax.annotation.Nonnull;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockLiquidFluid extends BlockFluidClassic{
	public BlockLiquidFluid(Fluid fluid) {
		super(fluid, Material.WATER);

//		setCreativeTab(TinkerRegistry.tabSmeltery);
	}

	@Nonnull
	@Override
	public String getUnlocalizedName() {
		Fluid fluid = FluidRegistry.getFluid(fluidName);
		if(fluid != null) {
			return fluid.getUnlocalizedName();
		}
		return super.getUnlocalizedName();
	}
}
