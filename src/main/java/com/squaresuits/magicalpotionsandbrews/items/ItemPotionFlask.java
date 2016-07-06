package com.squaresuits.magicalpotionsandbrews.items;

import java.util.List;
import javax.annotation.Nullable;

import com.squaresuits.magicalpotionsandbrews.util.IColorItem;
import com.squaresuits.magicalpotionsandbrews.util.flaskUtil;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPotionFlask extends Item implements IColorItem{
	private String material;
	public ItemPotionFlask()
    {
        this.setMaxStackSize(1);
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
    @Override
	@Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
    	if(!stack.getTagCompound().getBoolean("isEmpty")){
        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
        {
            stack.getTagCompound().setInteger("uses",stack.getTagCompound().getInteger("uses") - 1);
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
            if (stack.getTagCompound().getInteger("uses") <= 0)
            {
            	stack.getTagCompound().setInteger("uses",0);
            	stack.getTagCompound().setBoolean("isEmpty", true);
            	stack.getTagCompound().setString("Potion","minecraft:empty");
            }
        }
    	}

        return stack;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
	public int getMaxItemUseDuration(ItemStack stack)
    {
    	if(!stack.getTagCompound().getBoolean("isEmpty")){
    		return 32;
    	} else {
    		return 0;
    	}

    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
    	if(!stack.getTagCompound().getBoolean("isEmpty")){
    		return EnumAction.DRINK;
    	} else {
    		return EnumAction.NONE;
    	}
    }

    @Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
    	if(!itemStackIn.getTagCompound().getBoolean("isEmpty")){
    		playerIn.setActiveHand(hand);
        	return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
    	} else {
    		return new ActionResult(EnumActionResult.PASS, itemStackIn);
    	}
    }

    /*
     * Returns the display name of the flask.
     * @see net.minecraft.item.Item#getItemStackDisplayName(net.minecraft.item.ItemStack)
     */
    @Override
	public String getItemStackDisplayName(ItemStack stack)
    {
        return "Flask";
    	//return I18n.translateToLocal(PotionUtils.getPotionFromItem(stack).getNamePrefixed("potion.effect."));
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
    	if(stack.hasTagCompound()) {
    		tooltip.add("Material: " + stack.getTagCompound().getString("flaskComponent"));
    		tooltip.add("Glass: " + stack.getTagCompound().getString("infusedGlass"));
    		tooltip.add(stack.getTagCompound().getInteger("uses") + "/" + stack.getTagCompound().getInteger("maxUses"));
    		if(!stack.getTagCompound().getBoolean("isEmpty")){
        		PotionUtils.addPotionTooltip(stack, tooltip, 1.0F);
        	} else {
        		
        		tooltip.add(TextFormatting.GRAY + "Empty");

        	}
    	}
    	
    }

    @Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return !PotionUtils.getEffectsFromStack(stack).isEmpty();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getColor(){
        return new IItemColor(){
            @Override
            public int getColorFromItemstack(ItemStack stack, int pass){
            	if(!stack.hasTagCompound()){
            		pass = -1;
            	}
            	switch(pass){
            	case 0: //Glass
            		return 0xFFFFFF; //flaskUtil.materialColor.get(stack.getTagCompound().getString("infusedGlass"));
				case 1: //Fluid
            		return PotionUtils.getPotionColor(PotionUtils.getPotionFromItem(stack));
				case 2: //Metal
            		return flaskUtil.materialColor.get(stack.getTagCompound().getString("flaskComponent"));
				default:
            		return 0xFFFFFF;
            	}

               //pass > 0 ? (stack.getItemDamage() >= ALL_JAMS.length ? 0xFFFFFF : ALL_JAMS[stack.getItemDamage()].color) : 0xFFFFFF;
            }
        };
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
