package com.squaresuits.magicalpotionsandbrews.event;

import com.squaresuits.magicalpotionsandbrews.Main;
import com.squaresuits.magicalpotionsandbrews.init.Items;
import com.squaresuits.magicalpotionsandbrews.items.ItemPotionFlask;
import com.squaresuits.magicalpotionsandbrews.packet.PacketHandler;
import com.squaresuits.magicalpotionsandbrews.packet.messages.PotionSwitchMessage;
import com.squaresuits.magicalpotionsandbrews.proxy.ClientProxy;
import static com.squaresuits.magicalpotionsandbrews.Main.proxy;
import static com.squaresuits.magicalpotionsandbrews.items.ItemPotionFlask.POTIONHELD;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

/**
 * Created by Jeffrey on 7/13/2016.
 */
public class MPBEventHandler {
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        //Main.logger.info("You were hurt");
        if (!event.getEntity().getEntityWorld().isRemote && event.getEntity() instanceof EntityPlayer) {
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

    @SubscribeEvent
    public void mouse(MouseEvent event) {
        boolean cancel = false;
        if (Keyboard.isCreated()) {
            if ( Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && proxy.getPlayer().inventory.getCurrentItem() != null && proxy.getPlayer().inventory.getCurrentItem().getItem() instanceof ItemPotionFlask) {
                ItemStack flaskStack = proxy.getPlayer().inventory.getCurrentItem();
                ItemPotionFlask flask = (ItemPotionFlask) flaskStack.getItem();
                int selectedElement = flaskStack.getTagCompound().getInteger("potionSelected");
                int maxUses = flask.flaskGlassInfo.get(flaskStack.getTagCompound().getString("infusedGlass"))[POTIONHELD] - 1;
                if (event.getDwheel() != 0) {
                    if (event.getDwheel() > 0) {
                        if(selectedElement > 0) {
                            selectedElement--;
                        } else {
                            selectedElement = maxUses;
                        }
                    } else if (event.getDwheel() < 0) {
                        if(selectedElement < maxUses) {
                            selectedElement++;
                        } else {
                            selectedElement = 0;
                        }
                    }
                    //Main.logger.info(selectedElement);
                    PacketHandler.INSTANCE.sendToServer(new PotionSwitchMessage(selectedElement));
                }
                /*if (e.button == 0 && e.buttonstate) {
                    SpellHandler.addElement(staffStack, ElementRegistry.getElements().get(selectedElement));
                    PacketHandler.INSTANCE.sendToServer(new MessageAddElement(ElementRegistry.getElements().get(selectedElement)));
                } else if (e.button == 1 && e.buttonstate) {
                    SpellHandler.clearElements(staffStack);
                    PacketHandler.INSTANCE.sendToServer(new MessageClearElements());
                }*/
                cancel = true;
            }
            /*if (MineMagicka.proxy.getPlayer().getCurrentEquippedItem() != null && MineMagicka.proxy.getPlayer().getCurrentEquippedItem().isItemEqual(new ItemStack(MMItems.staff)) && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && e.button == 1 && e.buttonstate) {
                ISpell spell = SpellRegistry.getSpellFromElements(SpellHandler.getElements(MineMagicka.proxy.getPlayer().getCurrentEquippedItem()));
                if (spell != null) {
                    PacketHandler.INSTANCE.sendToServer(new MessageCastSpell(spell, MineMagicka.proxy.getPlayer().posX, MineMagicka.proxy.getPlayer().posY, MineMagicka.proxy.getPlayer().posZ));
                }
            }*/
        }
        event.setCanceled(cancel);
    }
}
