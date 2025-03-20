package com.github.ysbbbbbb.kaleidoscopedoll.event;

import com.github.ysbbbbbb.kaleidoscopedoll.KaleidoscopeDoll;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollBlock;
import com.github.ysbbbbbb.kaleidoscopedoll.item.DollItem;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class ModRegisterEvent {
    public static final Map<ResourceLocation, DollBlock> DOLL_BLOCKS = Maps.newHashMap();
    public static final Set<Item> DOLL_ITEMS = Sets.newLinkedHashSet();
    private static final int MAX_DOLL_COUNT = 72;
    private static final Map<ResourceLocation, String> SPECIAL_TOOLTIPS = Maps.newHashMap();

    private static void registerAllSpecialTooltips() {
        registerSpecialTooltips("doll_0", "author_ysbb");
        registerSpecialTooltips("doll_1", "author_tartaric_acid");
        registerSpecialTooltips("doll_67", "author_abert_cat");
        registerSpecialTooltips("doll_68", "author_cr_019");

        registerSpecialTooltips("doll_69", "sponsors_guriformes");
        registerSpecialTooltips("doll_70", "sponsors_kupurrra");
        registerSpecialTooltips("doll_71", "sponsors_tanyeng");
    }

    public static void registerBlocks() {
        registerAllSpecialTooltips();
        // 批量注册玩偶
        IntStream.range(0, MAX_DOLL_COUNT).forEach(i -> {
            ResourceLocation name = new ResourceLocation(KaleidoscopeDoll.MOD_ID, "doll_" + i);
            DollBlock block = new DollBlock();
            Item item = new DollItem(block, SPECIAL_TOOLTIPS.getOrDefault(name, "vanilla"));
            DOLL_BLOCKS.put(name, block);
            DOLL_ITEMS.add(item);

            Registry.register(BuiltInRegistries.BLOCK, name, block);
            Registry.register(BuiltInRegistries.ITEM, name, item);
        });
    }

    private static void registerSpecialTooltips(String name, String tooltip) {
        ResourceLocation id = new ResourceLocation(KaleidoscopeDoll.MOD_ID, name);
        SPECIAL_TOOLTIPS.put(id, tooltip);
    }
}
