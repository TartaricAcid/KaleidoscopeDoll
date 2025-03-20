package com.github.ysbbbbbb.kaleidoscopedoll.init;

import com.github.ysbbbbbb.kaleidoscopedoll.KaleidoscopeDoll;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollBlock;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollGiftBoxBlock;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollMachineBlock;
import com.github.ysbbbbbb.kaleidoscopedoll.event.ModRegisterEvent;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class ModBlocks {
    public static final Block DOLL_MACHINE = new DollMachineBlock();
    public static final Block PURPLE_DOLL_GIFT_BOX = new DollGiftBoxBlock();
    public static final Block GREEN_DOLL_GIFT_BOX = new DollGiftBoxBlock();
    public static final Block YELLOW_DOLL_GIFT_BOX = new DollGiftBoxBlock();

    public static void registerBlocks() {
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(KaleidoscopeDoll.MOD_ID, "doll_machine"), DOLL_MACHINE);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(KaleidoscopeDoll.MOD_ID, "purple_doll_gift_box"), PURPLE_DOLL_GIFT_BOX);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(KaleidoscopeDoll.MOD_ID, "green_doll_gift_box"), GREEN_DOLL_GIFT_BOX);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(KaleidoscopeDoll.MOD_ID, "yellow_doll_gift_box"), YELLOW_DOLL_GIFT_BOX);
        ModRegisterEvent.registerBlocks();
    }
}
