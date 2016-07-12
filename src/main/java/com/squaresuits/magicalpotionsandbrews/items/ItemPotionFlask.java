package com.squaresuits.magicalpotionsandbrews.items;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.squaresuits.magicalpotionsandbrews.util.IColorItem;
import com.squaresuits.magicalpotionsandbrews.Main;

import static java.util.Arrays.asList;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

    //Material constants//
    public static final int COPPER 			= 0;
    public static final int IRON 			= 1;
    public static final int GOLD 			= 2;
    public static final int DIAMOND 		= 3;
    public static final int STARSTEEL 		= 4;
    public static final int FYRESTONE 		= 5;
    public static final int EARTHSTONE 		= 6;


    public static final int MATINT = 0;
    public static final int MATUSE = 1;
    public static final int MATCOLOR = 2;

    public static final int GLASSMETA = 0;

    public ArrayList<String> flaskMaterials = new ArrayList<>(asList("iron", "gold"));
    public ArrayList<String> glassMaterials = new ArrayList<>(asList("pyrite", "diamond"));
    public LinkedHashMap<String, Integer[]> flaskMaterialInfo = new LinkedHashMap<String, Integer[]>(){{
        //								INT 			USE	Color
        put("copper",new Integer[] 		{COPPER		,	4,	0xEDA726});
        put("iron",new Integer[] 		{IRON		,	5,	0xB0B0B0});
        put("gold",new Integer[] 		{GOLD		,	10,	0xE8DA10});
        put("diamond",new Integer[]		{DIAMOND	, 	15, 0x1BDEBD}); //Legacy
        put("starsteel",new Integer[] 	{STARSTEEL	,	25,	0x1C1C1C});
        put("fyrestone", new Integer[]	{FYRESTONE	,	 6,	0xDB4725});
        put("earthstone", new Integer[]	{EARTHSTONE	, 	15, 0xA8840C});
    }};
    public LinkedHashMap<String, Integer[]> flaskGlassInfo = new LinkedHashMap<String, Integer[]>(){{
        //								META
        put("pyrite",new Integer[] 		{0});
        put("diamond",new Integer[] 	{1});
    }};
    public LinkedHashMap<String, String> effectName = new LinkedHashMap<String, String>(){{

        put("copper", "");
        put("iron", "");
        put("gold", TextFormatting.GREEN + "Lucky");
        put("starsteel", TextFormatting.LIGHT_PURPLE + "Plentyful");
        put("fyrestone", "");
        put("earthstone", "");
        put("pyrite", "");
        put("diamond","");
    }};


	public ItemPotionFlask()
	{
		this.setMaxStackSize(1);

		//this.setCreativeTab(CreativeTabs.BREWING);
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

	private void copperAbility(PotionEffect potioneffect, ItemStack stack, EntityLivingBase entityLiving){
        int duration = potioneffect.getDuration();
        if(duration != 1){
            for(int i = 0; i < Math.abs(stack.getTagCompound().getInteger("uses") - 4); i++){
                duration += 400;
            }
        }
        entityLiving.addPotionEffect(new PotionEffect(potioneffect.getPotion(), duration, potioneffect.getAmplifier(), potioneffect.getIsAmbient(), potioneffect.doesShowParticles()));
    }

	private void ironAbility(){
        //TODO Add ability here...
        Main.logger.info("No ability for IRON");
	}

	private void goldAbility(){
        //TODO Add ability here...
        Main.logger.info("No ability for GOLD");
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
						copperAbility(potioneffect, stack, entityLiving);
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
        if (!itemStackIn.getTagCompound().getBoolean("isEmpty")) {
            playerIn.setActiveHand(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
        } else {
            return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
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
        return (stack, pass) -> {
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
        };
    }

    public static String getNameFromMeta(int meta){
        switch(meta){
            case 0:
                return "pyrite";
            case 1:
                return "diamond";
            default:
                return "none";
        }
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
