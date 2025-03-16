package com.github.ysbbbbbb.kaleidoscopedoll.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GeneralConfig {
    public static ForgeConfigSpec.IntValue YELLOW_DOLL_GIFT_BOX_WEIGHT;
    public static ForgeConfigSpec.IntValue GREEN_DOLL_GIFT_BOX_WEIGHT;
    public static ForgeConfigSpec.IntValue PURPLE_DOLL_GIFT_BOX_WEIGHT;

    public static ForgeConfigSpec init() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("doll");

        YELLOW_DOLL_GIFT_BOX_WEIGHT = builder.defineInRange("YellowDollGiftBoxWeight", 10, 0, 100);
        GREEN_DOLL_GIFT_BOX_WEIGHT = builder.defineInRange("GreenDollGiftBoxWeight", 10, 0, 100);
        PURPLE_DOLL_GIFT_BOX_WEIGHT = builder.defineInRange("PurpleDollGiftBoxWeight", 80, 0, 100);

        builder.pop();
        return builder.build();
    }
}
