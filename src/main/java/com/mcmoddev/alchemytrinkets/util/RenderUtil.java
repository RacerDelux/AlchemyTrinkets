package com.mcmoddev.alchemytrinkets.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;


public class RenderUtil {
    public static final float root2over2 = (float)Math.sqrt(2.0f)/2.0f;
    public static int lightx = 0xF000F0;
    public static int lighty = 0xF000F0;

    public static void drawCenteredString(FontRenderer font, String string, int x, int y, int color){
        font.drawString(string, x-font.getStringWidth(string)/2, y, color);
    }

    @SideOnly(Side.CLIENT)
    public static ArrayList<String> getLines(String s, int lineWidth){
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> words = new ArrayList<String>();
        String temp = "";
        int counter = 0;
        for (int i = 0; i < s.length(); i ++){
            temp += s.charAt(i);
            if (s.charAt(i) == ' '){
                words.add(temp);
                temp = "";
            }
        }
        words.add(temp);
        temp = "";
        for (int i = 0; i < words.size(); i ++){
            counter += Minecraft.getMinecraft().fontRenderer.getStringWidth(words.get(i));
            if (counter > lineWidth){
                list.add(temp);
                temp = words.get(i);
                counter = Minecraft.getMinecraft().fontRenderer.getStringWidth(words.get(i));
            }
            else {
                temp += words.get(i);
            }
        }
        list.add(temp);
        return list;
    }
}
