package com.squaresuits.magicalpotionsandbrews.proxy;

import com.squaresuits.magicalpotionsandbrews.MPBGlobal;
import com.squaresuits.magicalpotionsandbrews.init.Blocks;
import com.squaresuits.magicalpotionsandbrews.init.Fluids;
import com.squaresuits.magicalpotionsandbrews.init.Items;
import com.squaresuits.magicalpotionsandbrews.util.IColorItem;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
	@Override
	public void preInit(FMLPreInitializationEvent preEvent){
		super.preInit(preEvent);
		
		Fluids.bakeModels(MPBGlobal.MOD_ID);
		
		
	}
	
	@Override
	public void init(FMLInitializationEvent event){
		super.init(event);
		

		Items.regItemRenders(event);
		Blocks.regBlockRenders(event);
		
		
		
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(((IColorItem)Items.potion_flask).getColor(), Items.potion_flask);
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent){
		super.postInit(postEvent);
	}
}
