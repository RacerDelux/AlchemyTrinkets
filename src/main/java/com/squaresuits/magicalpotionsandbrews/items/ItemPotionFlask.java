package com.squaresuits.magicalpotionsandbrews.items;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.squaresuits.magicalpotionsandbrews.init.Items;
import com.squaresuits.magicalpotionsandbrews.util.IColorItem;
import com.squaresuits.magicalpotionsandbrews.Main;

import static java.util.Arrays.asList;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPotionFlask extends Item implements IColorItem{

    //Material constants//
    public static final int COPPER 			= 0;
    public static final int IRON 			= 1;
    public static final int GOLD 			= 2;
    public static final int DIAMONDM 		= 3;
    public static final int STARSTEEL 		= 4;
    public static final int FYRESTONE 		= 5;
    public static final int EARTHSTONE 		= 6;

    public static final int PYRITE          = 0;
    public static final int DIAMOND         = 1;
    public static final int EMERALD         = 2;

    public static final int MATINT = 0;
    public static final int MATUSE = 1;
    public static final int MATCOLOR = 2;

    public static final int GLASSMETA = 0;
    public static final int POTIONHELD = 1;

    public ArrayList<String> flaskMaterials = new ArrayList<>(asList("iron", "gold"));
    public ArrayList<String> glassMaterials = new ArrayList<>(asList("pyrite", "diamond", "emerald"));

    public LinkedHashMap<String, Integer[]> flaskMaterialInfo = new LinkedHashMap<String, Integer[]>(){{
        //								INT 			USE	    Color
        put("copper",new Integer[] 		{COPPER		,	4   ,	0xEDA726});
        put("iron",new Integer[] 		{IRON		,	5   ,	0xB0B0B0});
        put("gold",new Integer[] 		{GOLD		,	10  ,	0xE8DA10});
        put("diamond",new Integer[]		{DIAMONDM	, 	15  ,   0x1BDEBD}); //Legacy
        put("starsteel",new Integer[] 	{STARSTEEL	,	25  ,	0x1C1C1C});
        put("fyrestone", new Integer[]	{FYRESTONE	,	6   ,	0xDB4725});
        put("earthstone", new Integer[]	{EARTHSTONE	, 	15  ,   0xA8840C});
    }};
    public LinkedHashMap<String, Integer[]> flaskGlassInfo = new LinkedHashMap<String, Integer[]>(){{
        //								META            #POTION HELD
        put("pyrite",new Integer[] 		{PYRITE     ,   2});
        put("diamond",new Integer[] 	{DIAMOND    ,   4});
        put("emerald",new Integer[]     {EMERALD    ,   6});
    }};
    public LinkedHashMap<String, String> effectName = new LinkedHashMap<String, String>(){{

        put("copper"        , TextFormatting.DARK_BLUE      + "Frugal");
        put("iron"          , TextFormatting.YELLOW         + "Defensive");
        put("gold"          , TextFormatting.GREEN          + "Lucky");
        put("starsteel"     , TextFormatting.LIGHT_PURPLE   + "Plentiful");
        put("fyrestone"     , TextFormatting.RED            + "Fiery");
        put("earthstone"    , TextFormatting.DARK_GREEN     + "Steadfast");
        put("pyrite"        , "");
        put("diamond"       , "");
        put("emerald"       , "");
    }};


	public ItemPotionFlask()
	{
		this.setMaxStackSize(1);

		//this.setCreativeTab(CreativeTabs.BREWING);
        this.addPropertyOverride(new ResourceLocation("infusedGlass"), new IItemPropertyGetter()
        {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if(stack.hasTagCompound()){
                    return ((ItemPotionFlask) Items.potion_flask).flaskGlassInfo.get(stack.getTagCompound().getString("infusedGlass"))[MATINT];
                }
                return 0;
            }
        });
	}

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    @Override
    @Nullable
    public ItemStack onItemUseFinish(@Nonnull ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (!worldIn.isRemote)
        {
            if(stack.hasTagCompound()) {
                if (!stack.getTagCompound().getBoolean("isEmpty")) {
                    EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer) entityLiving : null;

                    for (PotionEffect potioneffect : PotionUtils.getEffectsFromStack(stack)) {
                        switch (flaskMaterialInfo.get(stack.getTagCompound().getString("flaskComponent"))[MATINT]) {
                            case COPPER:
                                copperAbility(potioneffect, stack, entityLiving);
                                break;
                            case IRON:
                                ironAbility(potioneffect, entityLiving);
                                break;
                            case FYRESTONE:
                                fyrestoneAbility(potioneffect, entityLiving);
                                break;
                            default:
                                entityLiving.addPotionEffect(new PotionEffect(potioneffect));
                                break;
                        }
                    }

                    if (entityplayer != null) {
                        entityplayer.addStat(StatList.getObjectUseStats(this));
                    }


                    if (entityplayer == null || !entityplayer.capabilities.isCreativeMode) {
                        switch (flaskMaterialInfo.get(stack.getTagCompound().getString("flaskComponent"))[MATINT]) {
                            case GOLD:
                                goldAbility(stack);
                                break;
                            default:
                                stack.getTagCompound().setInteger("uses", stack.getTagCompound().getInteger("uses") - 1);

                                if (stack.getTagCompound().getInteger("uses") <= 0) {
                                    NBTTagCompound tag = stack.getTagCompound();
                                    tag.setInteger("uses", 0);
                                    tag.setBoolean("isEmpty", true);
                                    tag.setString("Potion", "");

                                    int current = tag.getInteger("potionSelected");
                                    tag.setInteger("uses" + Integer.toString(current), 0);
                                    tag.setString("Potion" + Integer.toString(current), "");
                                }
                                break;
                        }
                    }
                }
            }
            return stack;
        }
        return stack;
    }


    /**
     * Called every tick - used for flask abilities.
     * @param stack The ItemStack which is called the update.
     * @param world World the player is in.
     * @param entity Entity that owns the item.
     * @param slot Slot the item is in.
     * @param selected Is the item selected?
     */
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected){

		if (!world.isRemote) {

            if (stack.hasTagCompound()) {
                switch (flaskMaterialInfo.get(stack.getTagCompound().getString("flaskComponent"))[MATINT]) {

                    case STARSTEEL:

                        if (slot <= 8) {
                            if (entity.ticksExisted % 1200 == 0 && entity.ticksExisted != 0) {  //replace 20 with whatever tick number
                                NBTTagCompound tag = stack.getTagCompound();
                                if (tag == null) {
                                    tag = new NBTTagCompound();
                                }
                                int timer = tag.getInteger("matCD");
                                if (timer >= 14) {
                                    starsteelAbility(entity, slot, tag, stack);
                                    return;
                                } else {
                                    increaseCD(tag, timer);
                                    return;
                                }
                            }

                        }
                        break;

                    default:
                        break;
                }
            }
        }
	}

    public void onDamageEventActivated(LivingHurtEvent event, ItemStack stack){
            switch (flaskMaterialInfo.get(stack.getTagCompound().getString("flaskComponent"))[MATINT]) {

                case EARTHSTONE:
                    Main.logger.info("There is a earthstone flask that activated!");
                    if(event.getSource().getEntity() instanceof EntityLiving){
                        EntityLiving source = (EntityLiving) event.getSource().getEntity();
                        Main.logger.info("The damage source was from " + source.getName());
                        source.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 100));
                        Vec3d a = event.getEntity().getPositionVector();
                        Vec3d b = source.getPositionVector();
                        Vec3d motion = (a.subtract(b)).normalize();
                        source.knockBack(event.getEntity(),0.7f,motion.xCoord,motion.zCoord);
                    }
                    break;

                default:
                    break;
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

	private void ironAbility(PotionEffect potioneffect, EntityLivingBase entityLiving){
        entityLiving.addPotionEffect(new PotionEffect(potioneffect));
        entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(22), 600));
	}

	private void goldAbility(ItemStack stack){
        Random ran = new Random();
        if (ran.nextInt((100) + 1) < 50) {
            Main.logger.info("Flask was not used!");
        } else {
            stack.getTagCompound().setInteger("uses", stack.getTagCompound().getInteger("uses") - 1);
        }
        if (stack.getTagCompound().getInteger("uses") <= 0) {
            stack.getTagCompound().setInteger("uses", 0);
            stack.getTagCompound().setBoolean("isEmpty", true);
            stack.getTagCompound().setString("Potion", "minecraft:empty");

            NBTTagCompound tag = stack.getTagCompound();
            int current = tag.getInteger("potionSelected");
            tag.setInteger("uses" + Integer.toString(current), 0);
            tag.setString("Potion" + Integer.toString(current), "");
        }
	}

    private void fyrestoneAbility(PotionEffect potioneffect, EntityLivingBase entityLiving) {
        entityLiving.addPotionEffect(new PotionEffect(potioneffect));
        entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 600));
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
    @Nonnull
	public EnumAction getItemUseAction(ItemStack stack)
	{
        if(stack.hasTagCompound()) {
            if (!stack.getTagCompound().getBoolean("isEmpty")) {
                return EnumAction.DRINK;
            } else {
                return EnumAction.NONE;
            }
        } else {
            return EnumAction.NONE;
        }
	}

	@Override
    @Nonnull
	public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
        if(itemStackIn.hasTagCompound()) {
            if (!itemStackIn.getTagCompound().getBoolean("isEmpty")) {
                playerIn.setActiveHand(hand);
                return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
            } else {
                return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
            }
        } else {
            return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
        }
    }

    /**
     * Returns the display name of the flask
     * @param stack ItemStack of the item to be names.
     * @return Returns the display name.
     */
	@Override
    @Nonnull
	public String getItemStackDisplayName(@Nonnull ItemStack stack)
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
		    NBTTagCompound tag = stack.getTagCompound();
			if(effectName.get(tag.getString("flaskComponent")).equals("")){
				tooltip.add("Material: " + tag.getString("flaskComponent"));
			} else {
				tooltip.add(effectName.get(tag.getString("flaskComponent")));
			}
            String selected = "";
            for(int i = 0; i < flaskGlassInfo.get(tag.getString("infusedGlass"))[POTIONHELD]; i++){
                if(i == tag.getInteger("potionSelected")){
                    if(tag.getString("Potion" + Integer.toString(i)).equals("") && !tag.getBoolean("isEmpty")){
                        tag.setString("Potion" + Integer.toString(i),tag.getString("Potion"));
                    }
                    if(tag.getString("Potion" + Integer.toString(i)).equals("")) {
                        selected += (TextFormatting.DARK_RED + "\u25CB");
                    } else {
                        selected += (TextFormatting.DARK_RED + Character.toString('\u25CF'));
                    }
                } else {
                    if(tag.getString("Potion" + Integer.toString(i)).equals("")) {
                        selected += (TextFormatting.WHITE + "\u25CB");
                    } else {
                        selected += (TextFormatting.WHITE + Character.toString('\u25CF'));
                    }
                }
            }
            tooltip.add(selected);
			tooltip.add(tag.getInteger("uses") + "/" + tag.getInteger("maxUses"));
			if(!tag.getBoolean("isEmpty")){
				PotionUtils.addPotionTooltip(stack, tooltip, 1.0F);
			} else {

				tooltip.add(TextFormatting.GRAY + "Empty");

			}
		}

	}

    /**
     * Adds some flask information to the displayName.
     * @param item
     * @param displayName
     * @return
     */
    public String getHighlightTip( ItemStack item, String displayName )
    {
        NBTTagCompound tag = item.getTagCompound();
        Integer current = tag.getInteger("potionSelected");
        Integer capacity = flaskGlassInfo.get(tag.getString("infusedGlass"))[POTIONHELD];
        return displayName + " [" + (current + 1) + "/" + capacity + "]";
    }

	public static void setPotion(int next, ItemStack stack){
	    NBTTagCompound tag = stack.getTagCompound();
        int previous = tag.getInteger("potionSelected");

        Main.logger.info("The previous slot was: " + previous);
        Main.logger.info("Sent to server, change to slot: " + next);
        Main.logger.info("The selected potion is: " + stack.getTagCompound().getString("Potion"));

        tag.setString("Potion" + Integer.toString(previous), tag.getString("Potion"));
        tag.setInteger("PotionUses" + Integer.toString(previous), tag.getInteger("uses"));
        tag.setString("Potion", tag.getString("Potion" + Integer.toString(next)));
        tag.setInteger("uses", tag.getInteger("PotionUses" + Integer.toString(next)));
        tag.setInteger("potionSelected", next);
        if(tag.getString("Potion").equals("")){
            tag.setBoolean("isEmpty", true);
        } else {
            tag.setBoolean("isEmpty",false);
        }
        Main.logger.info("-----------------");
        Main.logger.info("The previous potion was: " + stack.getTagCompound().getString("Potion" + Integer.toString(previous)));
        Main.logger.info("The current potion is: " + stack.getTagCompound().getString("Potion" + Integer.toString(next)));
        Main.logger.info("It should match: " + stack.getTagCompound().getString("Potion"));
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

	// /**
	// * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	// */
	/*@SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (PotionType potiontype : PotionType.REGISTRY)
        {
            subItems.add(PotionUtils.addPotionToItemStack(new ItemStack(itemIn), potiontype));
        }
    }*/
}
