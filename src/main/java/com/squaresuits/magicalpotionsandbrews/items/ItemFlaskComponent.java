package com.squaresuits.magicalpotionsandbrews.items;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


import com.squaresuits.magicalpotionsandbrews.init.Items;
import static com.squaresuits.magicalpotionsandbrews.items.ItemPotionFlask.MATINT;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFlaskComponent extends Item{
	

	
	public ItemFlaskComponent() {
		//super();
		this.setHasSubtypes(true);
		//this.setCreativeTab(CreativeTabs.MATERIALS);
		this.addPropertyOverride(new ResourceLocation("material"), new IItemPropertyGetter()
		{
		    @Override
			@SideOnly(Side.CLIENT)
		    public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
		    {
		    	if(stack.hasTagCompound()){
		        return ((ItemPotionFlask)Items.potion_flask).flaskMaterialInfo.get(stack.getTagCompound().getString("material"))[MATINT];
		    	}
		    	return 0;
		    }
		});
	}
	
	/**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
	@Nonnull
	public String getUnlocalizedName(ItemStack stack)
    {
    	if(stack.hasTagCompound()){
        return setName( super.getUnlocalizedName(), stack.getTagCompound().getString("material"));
    	}
    	return "None";
    }

	/**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
	@SideOnly(Side.CLIENT)
    public void getSubItems(@Nonnull Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (int i = 0; i < ((ItemPotionFlask)Items.potion_flask).flaskMaterials.size(); ++i)
        {
            subItems.add(setComponentNBT(new ItemStack(itemIn), ((ItemPotionFlask)Items.potion_flask).flaskMaterials.get(i)));
        }
    }
    private ItemStack setComponentNBT(ItemStack itemIn, String string) {
        NBTTagCompound nbttagcompound = itemIn.hasTagCompound() ? itemIn.getTagCompound() : new NBTTagCompound();
        nbttagcompound.setString("material", string);
        itemIn.setTagCompound(nbttagcompound);
		return itemIn;
	}

	private String setName(String input, String material){
    	String[] base = input.split(Pattern.quote("."), 3);
    	String first = base[0];
    	String second = base[1];
    	String third = base[2];
    	return first + "." + second + "." + material + "_" + third;
    }
}
