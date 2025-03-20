package com.github.ysbbbbbb.kaleidoscopedoll.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GeneralConfig {
    public static ModConfigSpec.IntValue YELLOW_DOLL_GIFT_BOX_WEIGHT;
    public static ModConfigSpec.IntValue GREEN_DOLL_GIFT_BOX_WEIGHT;
    public static ModConfigSpec.IntValue PURPLE_DOLL_GIFT_BOX_WEIGHT;

    public static ModConfigSpec init() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("doll");

        YELLOW_DOLL_GIFT_BOX_WEIGHT = builder.defineInRange("YellowDollGiftBoxWeight", 10, 0, 100);
        GREEN_DOLL_GIFT_BOX_WEIGHT = builder.defineInRange("GreenDollGiftBoxWeight", 10, 0, 100);
        PURPLE_DOLL_GIFT_BOX_WEIGHT = builder.defineInRange("PurpleDollGiftBoxWeight", 80, 0, 100);

        builder.pop();
        return builder.build();
    }
}
