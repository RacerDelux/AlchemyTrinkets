package com.mcmoddev.alchemytrinkets.init;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;


public class tconPlugin {
	private static boolean initDone = false;

	public static void initTconPlugin() {
		if(initDone)return;

		
//			registerFluid(MPBFluids.fluidAluminum); // TCon already has this
//			registerFluid(MPBFluids.fluidAluminumBrass); // TCon already has this

//			registerAlloy("aluminumbrass", 2, "aluminum", 1, "brass", 1); // TCon already has Aluminum Brass alloy


		initDone = true;
	}
	
	public static void registerFluid(Fluid fluid) {
		// TODO: Make toolforge configurable
		if(Loader.isModLoaded("tconstruct")) {
			NBTTagCompound message = new NBTTagCompound();
			message.setString("fluid", fluid.getName());
			message.setString("ore", upperCaseFirst(fluid.getName()));
			message.setBoolean("toolforge", true);
			//message.setTag("alloy", alloysTagList); // you can also send an alloy with the registration (see below)

			// send the NBT to TCon
			FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", message);
		}
	}

	public static void registerAlloy(String outputName, int outputQty, String input1Name, int input1Qty, String input2Name, int input2Qty)
	{
		// TODO: Make this accept more than two input MPBFluids
		if(Loader.isModLoaded("tconstruct")) {
			NBTTagList tagList = new NBTTagList();
			
			// if you have access to the fluid-instance you can also do MPBFluidstack.writeToNBT
			NBTTagCompound fluid = new NBTTagCompound();
			fluid.setString("FluidName", outputName);
			fluid.setInteger("Amount", outputQty); // 144 = 1 ingot, 16 = 1 nugget
			tagList.appendTag(fluid);
		
			// first alloy fluid
			fluid = new NBTTagCompound();
			fluid.setString("FluidName", input1Name);
			fluid.setInteger("Amount", input1Qty);
			tagList.appendTag(fluid);
		
			// second alloy fluid
			fluid = new NBTTagCompound();
			fluid.setString("FluidName", input2Name);
			fluid.setInteger("Amount", input2Qty);
			tagList.appendTag(fluid);

			NBTTagCompound message = new NBTTagCompound();
			message.setTag("alloy", tagList);
			FMLInterModComms.sendMessage("tconstruct", "alloy", message);
		}
	}
	
	public static String upperCaseFirst(String value) {

		char[] array = value.toCharArray();
		array[0] = Character.toUpperCase(array[0]);
		return new String(array);
	}
	
	

}
