package com.squaresuits.magicalpotionsandbrews.init;

import java.util.HashMap;
import java.util.Map;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.blocks.BlockLiquidFluid;
import com.squaresuits.magicalpotionsandbrews.blocks.BlockMoltenFluid;
import com.squaresuits.magicalpotionsandbrews.fluids.CustomFluid;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Fluids {
	static {
		FluidRegistry.enableUniversalBucket();
	}

	public static Fluid fluidPyrite = null;
	public static Fluid fluidPotion = null;
	
	public static BlockFluidBase fluidBlockPyrite = null;
	public static BlockFluidBase fluidBlockPotion = null;
	

	private static final Map<Fluid,BlockFluidBase> fluidBlocks = new HashMap<>();
	private static final Map<BlockFluidBase,String> fluidBlockNames = new HashMap<>();

	private static boolean initDone = false;
	public static void initFluids() {

		// fluids
		fluidPyrite = newFluid(MPBGlobal.MOD_ID, "pyrite", 2000,10000,330,10, 0xFFC4B012);
		fluidPotion = newFluid(MPBGlobal.MOD_ID, "potion", 500,3000,60,3, 0x996868FF);

		// fluid blocks
		fluidBlockPyrite = registerFluidBlock(fluidPyrite, new BlockMoltenFluid(fluidPyrite),"pyrite");
		fluidBlockPotion = registerFluidBlock(fluidPotion, new BlockLiquidFluid(fluidPotion),"potion");
		
	}

	@SideOnly(Side.CLIENT)
	public static void bakeModels(String modID){
		for(Fluid fluid : fluidBlocks.keySet()){
			BlockFluidBase block = fluidBlocks.get(fluid);
			Item item = Item.getItemFromBlock(block);
			final ModelResourceLocation fluidModelLocation = new ModelResourceLocation(
					modID.toLowerCase() + ":" + fluidBlockNames.get(block), "fluid");
            ModelBakery.registerItemVariants(item);
			ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition()
			{
				public ModelResourceLocation getModelLocation(ItemStack stack)
				{
					return fluidModelLocation;
				}
			});
			ModelLoader.setCustomStateMapper(block, new StateMapperBase()
			{
				protected ModelResourceLocation getModelResourceLocation(IBlockState state)
				{
					return fluidModelLocation;
				}
			});
		}
	}
	

	private static Fluid newFluid(String modID, String name, int density, int viscosity, int temperature, int luminosity, int tintColor) {
		Fluid fluid = new CustomFluid(name, new ResourceLocation(modID+":blocks/molten_metal_still"), new ResourceLocation(modID+":blocks/molten_metal_flow"), tintColor);
		fluid.setDensity(density);
		fluid.setViscosity(viscosity);
		fluid.setTemperature(temperature);
		fluid.setLuminosity(luminosity);
		fluid.setUnlocalizedName(modID+"."+name);
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);
		return fluid;
	}

	private static BlockFluidClassic registerFluidBlock(Fluid fluid, BlockFluidClassic block, String name) {
		ResourceLocation location = new ResourceLocation(MPBGlobal.MOD_ID, name);
		block.setRegistryName(location);
		block.setUnlocalizedName(location.toString());
		GameRegistry.register(block);
		block.setCreativeTab(CreativeTabs.MISC);

		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(location);
		itemBlock.setUnlocalizedName(location.toString());
		GameRegistry.register(itemBlock);
		
		fluidBlocks.put(fluid, block);
		fluidBlockNames.put(block, name);
		return block;
	}
}
