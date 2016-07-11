package com.squaresuits.magicalpotionsandbrews.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.squaresuits.magicalpotionsandbrews.util.IColorItem;
import com.squaresuits.magicalpotionsandbrews.Main;
import com.squaresuits.magicalpotionsandbrews.util.FlaskUtil;
import static com.squaresuits.magicalpotionsandbrews.util.FlaskUtil.*;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
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
	 * Called every tick
	 */
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected){

		if (!world.isRemote){

			switch(flaskMaterialInfo.get(stack.getTagCompound().getString("flaskComponent"))[MATINT]){

			case STARSTEEL:

				if(slot <= 8){
					if (entity.ticksExisted % 1200 == 0 && entity.ticksExisted != 0){  //replace 20 with whatever tick number
						NBTTagCompound tag = stack.getTagCompound();
						if(tag == null){
							tag = new NBTTagCompound();
						}
						int timer = tag.getInteger("matCD");
						if(timer >= 14){
							starsteelAbility(entity, slot, tag, stack);
							return;
						} else {
							increaseCD(tag, timer);
							return;
						}
					}

				}
				break;

				//case GOLD:
				/*if (entity.ticksExisted % 500 == 0 && entity.ticksExisted != 0){  //replace 20 with whatever tick number
					if(entity instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) entity;
						//player.addPotionEffect(new PotionEffect(Potion.getPotionById(21),10000, 0));
						//player.heal(3);
						//player.getMaxHealth();
					}
				}*/
				//break;

			default:
				break;
			}
		}
	}
	// ABILITIES //

	private void ironAbility(){

	}

	private void goldAbility(){

	}

	private void starsteelAbility(Entity entity, int slot, NBTTagCompound tag, ItemStack stack){
		Main.logger.info("I was activated! " + entity.ticksExisted + ". I am in slot " + slot);
		int current = tag.getInteger("uses");
		Main.logger.info("The current number of uses: " + current + ". The max number of uses: " + tag.getInteger("maxUses"));
		if(current < tag.getInteger("maxUses") && current > 0){
			tag.setInteger("uses", current + 1);
		}
		stack.setTagCompound(tag);
		tag.setInteger("matCD", 0);
	}

	// END ABILITIES //

	private void increaseCD(NBTTagCompound tag, int timer){
		int test = timer + 1;
		tag.setInteger("matCD", test);
	}

	/**
	 * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
	 * the Item before the action is complete.
	 */
	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
		if (!worldIn.isRemote)
		{
			if(!stack.getTagCompound().getBoolean("isEmpty")){
				EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;

				for (PotionEffect potioneffect : PotionUtils.getEffectsFromStack(stack))
				{
					switch(flaskMaterialInfo.get(stack.getTagCompound().getString("flaskComponent"))[MATINT]){
					case COPPER:
						int duration = potioneffect.getDuration();
						if(duration != 1){
							for(int i = 0; i < Math.abs(stack.getTagCompound().getInteger("uses") - 4); i++){
								duration += 400;
							}
						}
						entityLiving.addPotionEffect(new PotionEffect(potioneffect.getPotion(), duration, potioneffect.getAmplifier(), potioneffect.getIsAmbient(), potioneffect.doesShowParticles()));
						break;
					default:
						entityLiving.addPotionEffect(new PotionEffect(potioneffect));
						break;
					}
				}

				if (entityplayer != null)
				{
					entityplayer.addStat(StatList.getObjectUseStats(this));
				}


				if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
				{
					switch(flaskMaterialInfo.get(stack.getTagCompound().getString("flaskComponent"))[MATINT]){
					case GOLD:
						Random ran = new Random();
						if(ran.nextInt((100) + 1) < 50){
							Main.logger.info("Flask was not used!");
						} else {
							stack.getTagCompound().setInteger("uses",stack.getTagCompound().getInteger("uses") - 1);
						}
						if (stack.getTagCompound().getInteger("uses") <= 0)
						{
							stack.getTagCompound().setInteger("uses",0);
							stack.getTagCompound().setBoolean("isEmpty", true);
							stack.getTagCompound().setString("Potion","minecraft:empty");
						}
						break;
					default:
						stack.getTagCompound().setInteger("uses",stack.getTagCompound().getInteger("uses") - 1);

						if (stack.getTagCompound().getInteger("uses") <= 0)
						{
							stack.getTagCompound().setInteger("uses",0);
							stack.getTagCompound().setBoolean("isEmpty", true);
							stack.getTagCompound().setString("Potion","minecraft:empty");
						}
						break;
					}
				}
			}

			return stack;
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
			if(effectName.get(stack.getTagCompound().getString("flaskComponent")).equals("")){
				tooltip.add("Material: " + stack.getTagCompound().getString("flaskComponent"));
			} else {
				tooltip.add(effectName.get(stack.getTagCompound().getString("flaskComponent")));
			}
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
					return flaskMaterialInfo.get(stack.getTagCompound().getString("flaskComponent"))[MATCOLOR];
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
