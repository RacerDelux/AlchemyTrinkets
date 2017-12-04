package com.mcmoddev.alchemytrinkets.packet;

import com.mcmoddev.alchemytrinkets.MPBGlobal;
import com.mcmoddev.alchemytrinkets.packet.messages.PotionSwitchMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Jeffrey on 7/18/2016.
 */
public class PacketHandler {

    public static SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper(MPBGlobal.MOD_ID);
    public static int ID = 0;

    public static void preInit() {
        INSTANCE.registerMessage(PotionSwitchMessage.class, PotionSwitchMessage.class, ID++, Side.SERVER);
    }

}
