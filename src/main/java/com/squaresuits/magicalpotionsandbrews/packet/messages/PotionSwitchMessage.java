package com.squaresuits.magicalpotionsandbrews.packet.messages;

import com.squaresuits.magicalpotionsandbrews.Main;
import com.squaresuits.magicalpotionsandbrews.items.ItemPotionFlask;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Jeffrey on 7/18/2016.
 */
public class PotionSwitchMessage implements IMessage, IMessageHandler<PotionSwitchMessage, IMessage> {

        public PotionSwitchMessage() {
        }
        private int selectedElement;

        public PotionSwitchMessage(Integer selectedElement) {
            this.selectedElement = selectedElement;//this.element = element.getUnlocalizedName();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            ByteBufUtils.writeVarInt(buf, selectedElement, 1);
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            this.selectedElement = ByteBufUtils.readVarInt(buf, 1);
        }

        @Override
        public IMessage onMessage(PotionSwitchMessage message, MessageContext ctx) {
            //TODO varify this is correct
            EntityPlayer entity = ctx.getServerHandler().player;
            if (entity != null) {
                ItemStack stack = entity.inventory.getCurrentItem();
                if (stack != null) {
                    ItemPotionFlask.setPotion(message.selectedElement, stack);
                    //SpellHandler.addElement(stack, ElementRegistry.getElementFromName(message.element));
                }
            }

            return null;
        }
}
