package com.squaresuits.magicalpotionsandbrews.proxy;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.Main;
import com.squaresuits.magicalpotionsandbrews.init.Blocks;
import com.squaresuits.magicalpotionsandbrews.init.Fluids;
import com.squaresuits.magicalpotionsandbrews.init.Items;
import com.squaresuits.magicalpotionsandbrews.util.IColorItem;
import com.squaresuits.magicalpotionsandbrews.util.flaskUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
	public void preInit(FMLPreInitializationEvent preEvent){
		super.preInit(preEvent);
		
		Fluids.bakeModels(MPBGlobal.MOD_ID);
		
		
	}
	
	public void init(FMLInitializationEvent event){
		super.init(event);
		

		Items.regItemRenders(event);
		Blocks.regBlockRenders(event);
		
		
		
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(((IColorItem)Items.potion_flask).getColor(), Items.potion_flask);
	}

	public void postInit(FMLPostInitializationEvent postEvent){
		super.postInit(postEvent);
	}
}
