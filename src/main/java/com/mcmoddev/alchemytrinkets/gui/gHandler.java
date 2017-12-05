package com.mcmoddev.alchemytrinkets.gui;

import com.mcmoddev.alchemytrinkets.book.bookRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class gHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID){
            case 0:
                return new gBook(bookRegistry.alchemy_book.name,player.getHeldItemMainhand());
            case 1:
                return new gBook(bookRegistry.alchemy_book.name,player.getHeldItemOffhand());
            case 2:
                return new gBook(bookRegistry.spellcraft_book.name,player.getHeldItemMainhand());
            case 3:
                return new gBook(bookRegistry.spellcraft_book.name,player.getHeldItemOffhand());
            case 4:
                return new gBook(bookRegistry.ritual_book.name,player.getHeldItemMainhand());
            case 5:
                return new gBook(bookRegistry.ritual_book.name,player.getHeldItemOffhand());
            case 6:
                return new gBook(bookRegistry.forbidden_book.name,player.getHeldItemMainhand());
            case 7:
                return new gBook(bookRegistry.forbidden_book.name,player.getHeldItemOffhand());
        }
        return null;
    }
}
