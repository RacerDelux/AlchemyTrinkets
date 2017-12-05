package com.mcmoddev.alchemytrinkets.book;

import java.util.ArrayList;

import com.mcmoddev.alchemytrinkets.MPBGlobal;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class book {
    public ArrayList<page> pages = new ArrayList<page>();
    public String name = "";
    public int selectedPage = 0;
    public book(String name){
        this.name = name;
    }

    public book addPage(page page){
        this.pages.add(page);
        return this;
    }

    public static ItemStack createData(ItemStack blankStack, float knowledge){
        if (!blankStack.hasTagCompound()){
            blankStack.setTagCompound(new NBTTagCompound());
        }
        ItemStack stack = blankStack.copy();
        stack.getTagCompound().setFloat(MPBGlobal.KNOWLEDGE, 0);
        return stack;
    }

    public static float getKnowledge(ItemStack stack){
        if (stack.hasTagCompound()){
            if (stack.getTagCompound().hasKey(MPBGlobal.KNOWLEDGE)){
                return stack.getTagCompound().getFloat(MPBGlobal.KNOWLEDGE);
            }
        }
        return 0.0f;
    }
}
