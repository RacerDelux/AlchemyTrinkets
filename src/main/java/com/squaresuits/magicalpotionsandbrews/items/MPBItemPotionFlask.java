package com.squaresuits.magicalpotionsandbrews.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MPBItemPotionFlask extends Item{
	
	private boolean isEmpty;
	private String material;
	public MPBItemPotionFlask()
    {
        this.setMaxStackSize(1);
        isEmpty = true;
        material = "none";
        //this.setCreativeTab(CreativeTabs.BREWING);
    }

	public void setMaterials(String string){
		material = string;
	}
	
    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    @Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
    	if(!isEmpty){
        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
        {
            --stack.stackSize;
        }

        if (!worldIn.isRemote)
        {
            for (PotionEffect potioneffect : PotionUtils.getEffectsFromStack(stack))
            {
                entityLiving.addPotionEffect(new PotionEffect(potioneffect));
            }
        }

        if (entityplayer != null)
        {
            entityplayer.addStat(StatList.getObjectUseStats(this));
        }

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
        {
            if (stack.stackSize <= 0)
            {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (entityplayer != null)
            {
                entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }
    	}

        return stack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
    	if(!isEmpty){
    		return 32;
    	} else {
    		return 0;
    	}

    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
    	if(!isEmpty){
    		return EnumAction.DRINK;
    	} else {
    		return EnumAction.NONE;
    	}
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
    	if(!isEmpty){
    		playerIn.setActiveHand(hand);
        	return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
    	} else {
    		return new ActionResult(EnumActionResult.PASS, itemStackIn);
    	}
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        return "Flask";
    	//return I18n.translateToLocal(PotionUtils.getPotionFromItem(stack).getNamePrefixed("potion.effect."));
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
    	if(stack.hasTagCompound()) {
    		tooltip.add("Material: " + stack.getTagCompound().getString("flaskComponent"));
    		tooltip.add("Glass: " + stack.getTagCompound().getString("infusedGlass"));
    	}
    	if(!isEmpty){
    		PotionUtils.addPotionTooltip(stack, tooltip, 1.0F);
    	} else {
    		
    		tooltip.add(TextFormatting.GRAY + material);

    	}
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return !PotionUtils.getEffectsFromStack(stack).isEmpty();
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    /*@SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (PotionType potiontype : PotionType.REGISTRY)
        {
            subItems.add(PotionUtils.addPotionToItemStack(new ItemStack(itemIn), potiontype));
        }
    }*/
}
