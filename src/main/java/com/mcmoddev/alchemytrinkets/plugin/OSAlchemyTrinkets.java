package com.mcmoddev.alchemytrinkets.plugin;

        import com.mcmoddev.orespawn.api.os3.*;
        import com.mcmoddev.orespawn.api.plugin.IOreSpawnPlugin;
        import com.mcmoddev.orespawn.api.plugin.OreSpawnPlugin;

@OreSpawnPlugin(modid = "magicpab", resourcePath = "orespawn")
public class OSAlchemyTrinkets implements IOreSpawnPlugin {

    @Override
    public void register(OS3API apiInterface) {
        // nothing for us to do - all of our ores are in the
        // jar and the code handles that
    }
}