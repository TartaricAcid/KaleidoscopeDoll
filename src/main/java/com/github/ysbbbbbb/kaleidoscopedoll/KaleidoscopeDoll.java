package com.github.ysbbbbbb.kaleidoscopedoll;

import com.github.ysbbbbbb.kaleidoscopedoll.config.GeneralConfig;
import com.github.ysbbbbbb.kaleidoscopedoll.init.ModBlocks;
import com.github.ysbbbbbb.kaleidoscopedoll.init.ModCreativeTabs;
import com.github.ysbbbbbb.kaleidoscopedoll.init.ModItems;
import com.mojang.logging.LogUtils;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

public class KaleidoscopeDoll implements ModInitializer {
    public static final String MOD_ID = "kaleidoscope_doll";
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        ForgeConfigRegistry.INSTANCE.register(MOD_ID, ModConfig.Type.COMMON, GeneralConfig.init());
        ModBlocks.registerBlocks();
        ModItems.registerItems();
        ModCreativeTabs.registerTabs();
    }
}
