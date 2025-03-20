package com.github.ysbbbbbb.kaleidoscopedoll.event;

import com.github.ysbbbbbb.kaleidoscopedoll.KaleidoscopeDoll;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollBlock;
import com.github.ysbbbbbb.kaleidoscopedoll.item.DollItem;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

@EventBusSubscriber(modid = KaleidoscopeDoll.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModRegisterEvent {
    private static final int MAX_DOLL_COUNT = 67;
    public static Map<ResourceLocation, DollBlock> DOLL_BLOCKS = Maps.newHashMap();
    public static Set<Item> DOLL_ITEMS = Sets.newLinkedHashSet();

    @SubscribeEvent
    public static void registerBlocks(RegisterEvent event) {
        // 批量注册玩偶
        if (event.getRegistryKey().equals(Registries.BLOCK)) {
            IntStream.range(2, MAX_DOLL_COUNT).forEach(i -> {
                ResourceLocation name = ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "doll_" + i);
                ResourceKey<Block> id = ResourceKey.create(Registries.BLOCK, name);
                DollBlock block = new DollBlock(BlockBehaviour.Properties.of().setId(id));
                DOLL_BLOCKS.put(name, block);
                event.register(Registries.BLOCK, name, () -> block);
            });
        }
        if (event.getRegistryKey().equals(Registries.ITEM)) {
            IntStream.range(2, MAX_DOLL_COUNT).forEach(i -> {
                ResourceLocation name = ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "doll_" + i);
                ResourceKey<Item> id = ResourceKey.create(Registries.ITEM, name);
                DollBlock block = DOLL_BLOCKS.get(name);
                Item item = new DollItem(block, new Item.Properties().setId(id));
                DOLL_ITEMS.add(item);
                event.register(Registries.ITEM, name, () -> item);
            });
        }
    }
}
