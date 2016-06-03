package com.squaresuits.magicalpotionsandbrews.render;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.init.MPBItems;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class MPBItemRender {
public static void registerItemRender() {
		
		//Ingot
		regItem(MPBItems.copper_ingot);
		regItem(MPBItems.nickel_ingot);
		
		//Shards
		regItem(MPBItems.pyrite_shard);
		
		//Gems
		regItem(MPBItems.topaz_stone);
		
	}
	
	public static void regItem(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,0, new ModelResourceLocation(MPBGlobal.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
