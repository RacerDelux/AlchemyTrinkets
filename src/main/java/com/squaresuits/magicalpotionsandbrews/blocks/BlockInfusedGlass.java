package com.squaresuits.magicalpotionsandbrews.blocks;

import java.util.List;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class BlockInfusedGlass extends BlockGlass{



	public BlockInfusedGlass(boolean ignoreSimilarity) {
		super(Material.GLASS, ignoreSimilarity);
		this.fullBlock = true;
		//this.lightOpacity = 255;
		//this.translucent = true;
		this.blockSoundType = SoundType.GLASS;
		this.blockHardness = (float) 0.3;

		//this.setHarvestLevel("pickaxe", metal.getRequiredHarvestLevel());
	}

	// create a list of the subBlocks available for this block, i.e. one for each material
	// ignores facings, because the facing is calculated when we place the item.
	//  - used to populate items for the creative inventory
	// - the "metadata" value of the block is set to the materials metadata
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(@Nonnull Item itemIn, CreativeTabs tab, List list)
	{
		EnumMat[] allMaterials = EnumMat.values();
		for (EnumMat material : allMaterials) {
			list.add(new ItemStack(itemIn, 1, material.getMetadata()));
		}
	}
	/*public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> subBlocks)
    {
		for (int i = 0; i < FlaskUtil.glassMaterials.size(); ++i)
        {
            subBlocks.add(new ItemStack(
            		Blocks.infused_glass_block,1,FlaskUtil.flaskGlassInfo.get(
            						FlaskUtil.glassMaterials.get(i))[FlaskUtil.GLASSMETA]));
        }
    }*/


	///// OVERRIDE OF ALL METHODS THAT DEPEND ON BLOCK MATERIAL: /////
	@Override
    @Nonnull
	public MapColor getMapColor(final IBlockState p_getMapColor_1_) {
		return MapColor.IRON;
	}

	// Our block has two properties:
	// 1) PROPERTYFACING for which way the sign points (east, west, north, south).  EnumFacing is as standard used by vanilla for a number of blocks.
	// 2) PROPERTYmaterial for the sign's material.  materialsEnum is a custom class (see below)
	//public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum PROPERTYNAME = PropertyEnum.create("material", EnumMat.class);

	// this function returns the correct item type corresponding to the material of our block;
	// i.e. when a sign is broken, it will drop the correct item.  Ignores Facing, because we get the same item
	//   no matter which way the block is facing
	@Override
	public int damageDropped(IBlockState state)
	{
		EnumMat enummaterial = (EnumMat)state.getValue(PROPERTYNAME);
		return enummaterial.getMetadata();
	}

	// getStateFromMeta, getMetaFromState are used to interconvert between the block's property values and
	//   the stored metadata (which must be an integer in the range 0 - 15 inclusive)
	// The property is encode as:
	// - lower two bits = facing direction (i.e. 0, 1, 2, 3)
	// - upper two bits = material (i.e. 0, 4, 8, 12)
	@Override
    @Nonnull
	public IBlockState getStateFromMeta(int meta)
	{
		EnumMat material = EnumMat.byMetadata(meta);
		return this.getDefaultState().withProperty(PROPERTYNAME, material);//.withProperty(PROPERTYFACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		EnumMat material = (EnumMat)state.getValue(PROPERTYNAME);

        return material.getMetadata();
	}

	/*// this method isn't required if your properties only depend on the stored metadata.
	// it is required if:
	// 1) you are making a multiblock which stores information in other blocks eg BlockBed, BlockDoor
	// 2) your block's state depends on other neighbours (eg BlockFence)
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		return state;
	}*/

	// necessary to define which properties your blocks use
	  // will also affect the variants listed in the blockstates model file
	  @Override
      @Nonnull
	  protected BlockStateContainer createBlockState()
	  {
	    return new BlockStateContainer(this, PROPERTYNAME);
	  }

	// when the block is placed, set the appropriate facing direction based on which way the player is looking
	// the material of block is contained in meta, it corresponds to the values we used for getSubBlocks
	@Override
    @Nonnull
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		EnumMat material = EnumMat.byMetadata(meta);
		return this.getDefaultState().withProperty(PROPERTYNAME, material);
	}

	// create a new enum for our four materials, with some supporting methods to convert to & from metadata, and to get
	//  human-readable names.
	public enum EnumMat implements IStringSerializable
	{
		PYRITE(0, "pyrite"),
		DIAMOND(1, "diamond"),
		EMERALD(2, "emerald");

		private final int meta;
		private final String name;
		private static final EnumMat[] META_LOOKUP = new EnumMat[values().length];
		static
		{
			for (EnumMat material : values()) {
				META_LOOKUP[material.getMetadata()] = material;
			}
		}

        EnumMat(int i_meta, String i_name)
		{
			this.meta = i_meta;
			this.name = i_name;
		}

		public int getMetadata()
		{
			return this.meta;
		}

		@Override
		public String toString()
		{
			return this.name;
		}

		public static EnumMat byMetadata(int meta)
		{
			if (meta < 0 || meta >= META_LOOKUP.length)
			{
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		@Override
        @Nonnull
		public String getName()
		{
			return this.name;
		}
	}
}
