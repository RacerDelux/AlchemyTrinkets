package com.squaresuits.magicalpotionsandbrews.event;

import com.squaresuits.magicalpotionsandbrews.Main;
import com.squaresuits.magicalpotionsandbrews.init.Items;
import com.squaresuits.magicalpotionsandbrews.items.ItemPotionFlask;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Jeffrey on 7/13/2016.
 */
public class MPBEventHandler {
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        //Main.logger.info("You were hurt");
        if (!event.getEntity().worldObj.isRemote && event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            InventoryPlayer inventory = player.inventory;
            for(int i = 0; i < 8; i++){
                ItemStack stack = inventory.getStackInSlot(i);
                if(stack != null && stack.hasTagCompound() && stack.getItem() instanceof ItemPotionFlask){
                    ((ItemPotionFlask)Items.potion_flask).onDamageEventActivated(event, stack);
                }
            }
        }
    }
}
