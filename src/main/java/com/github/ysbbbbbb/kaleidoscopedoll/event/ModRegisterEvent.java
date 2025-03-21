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

    @SubscribeEvent
    public static void registerBlocks(RegisterEvent event) {
        registerAllSpecialTooltips();
        // 批量注册玩偶
        if (event.getRegistryKey().equals(Registries.BLOCK)) {
            IntStream.range(0, MAX_DOLL_COUNT).forEach(i -> {
                ResourceLocation name = ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "doll_" + i);
                ResourceKey<Block> id = ResourceKey.create(Registries.BLOCK, name);
                DollBlock block = new DollBlock(BlockBehaviour.Properties.of().setId(id));
                DOLL_BLOCKS.put(name, block);
                event.register(Registries.BLOCK, name, () -> block);
            });
        }
        if (event.getRegistryKey().equals(Registries.ITEM)) {
            IntStream.range(0, MAX_DOLL_COUNT).forEach(i -> {
                ResourceLocation name = ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "doll_" + i);
                ResourceKey<Item> id = ResourceKey.create(Registries.ITEM, name);
                DollBlock block = DOLL_BLOCKS.get(name);
                Item item = new DollItem(block, new Item.Properties().setId(id), SPECIAL_TOOLTIPS.getOrDefault(name, "vanilla"));
                DOLL_ITEMS.add(item);
                event.register(Registries.ITEM, name, () -> item);
            });
        }
    }

    private static void registerSpecialTooltips(String name, String tooltip) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, name);
        SPECIAL_TOOLTIPS.put(id, tooltip);
    }
}
