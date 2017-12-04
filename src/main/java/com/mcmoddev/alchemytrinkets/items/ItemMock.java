package com.mcmoddev.alchemytrinkets.items;

import com.mcmoddev.alchemytrinkets.material.ResourceMaterial;
import net.minecraft.item.Item;

public class ItemMock extends Item {

	protected final ResourceMaterial metal;
	private final String oreDict;
	
	public ItemMock() {
		oreDict = "mock";
		this.metal = null;
		//super();
		//this.metal = metal;
		//this.oreDict = "shard"+metal.getCapitalizedName();
		//this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	public ResourceMaterial getMetalMaterial(){
		return metal;
	}
	
	public ResourceMaterial getMetal(){
		return metal;
	}
}
