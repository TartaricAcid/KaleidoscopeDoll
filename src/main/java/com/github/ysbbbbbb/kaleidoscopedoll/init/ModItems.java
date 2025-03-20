package com.github.ysbbbbbb.kaleidoscopedoll.init;

import com.github.ysbbbbbb.kaleidoscopedoll.KaleidoscopeDoll;
import com.github.ysbbbbbb.kaleidoscopedoll.item.DollItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public final class ModItems {
    public static final Item DOLL_MACHINE = new BlockItem(ModBlocks.DOLL_MACHINE, new Item.Properties());
    public static final Item DOLL_AUTHOR_YSBB = new DollItem(ModBlocks.DOLL_AUTHOR_YSBB);
    public static final Item DOLL_AUTHOR_943 = new DollItem(ModBlocks.DOLL_AUTHOR_943);
    public static final Item PURPLE_DOLL_GIFT_BOX = new BlockItem(ModBlocks.PURPLE_DOLL_GIFT_BOX, new Item.Properties());
    public static final Item GREEN_DOLL_GIFT_BOX = new BlockItem(ModBlocks.GREEN_DOLL_GIFT_BOX, new Item.Properties());
    public static final Item YELLOW_DOLL_GIFT_BOX = new BlockItem(ModBlocks.YELLOW_DOLL_GIFT_BOX, new Item.Properties());

    public static void registerItems() {
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "doll_machine"), DOLL_MACHINE);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "doll_0"), DOLL_AUTHOR_YSBB);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "doll_1"), DOLL_AUTHOR_943);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "purple_doll_gift_box"), PURPLE_DOLL_GIFT_BOX);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "green_doll_gift_box"), GREEN_DOLL_GIFT_BOX);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "yellow_doll_gift_box"), YELLOW_DOLL_GIFT_BOX);
    }
}
