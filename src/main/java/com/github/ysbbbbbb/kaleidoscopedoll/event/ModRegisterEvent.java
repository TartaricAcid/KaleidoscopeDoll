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
    private static final int MAX_DOLL_COUNT = 67;
    public static Map<ResourceLocation, DollBlock> DOLL_BLOCKS = Maps.newHashMap();
    public static Set<Item> DOLL_ITEMS = Sets.newLinkedHashSet();

    public static void registerBlocks() {
        // 批量注册玩偶
        IntStream.range(2, MAX_DOLL_COUNT).forEach(i -> {
            ResourceLocation name = new ResourceLocation(KaleidoscopeDoll.MOD_ID, "doll_" + i);
            DollBlock block = new DollBlock();
            Item item = new DollItem(block);
            DOLL_BLOCKS.put(name, block);
            DOLL_ITEMS.add(item);

            Registry.register(BuiltInRegistries.BLOCK, name, block);
            Registry.register(BuiltInRegistries.ITEM, name, item);
        });
    }
}
