package com.github.ysbbbbbb.kaleidoscopedoll.init;

import com.github.ysbbbbbb.kaleidoscopedoll.KaleidoscopeDoll;
import com.github.ysbbbbbb.kaleidoscopedoll.event.ModRegisterEvent;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class ModCreativeTabs {
    private static final ResourceLocation ICON_ID = ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "doll_52");
    private static final ResourceKey<CreativeModeTab> DOLL_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "doll"));

    public static void registerTabs() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, DOLL_TAB, FabricItemGroup.builder()
                .title(Component.translatable("item_group.kaleidoscope_doll.doll.name"))
                .icon(() -> BuiltInRegistries.ITEM.get(ICON_ID).getDefaultInstance())
                .displayItems((par, output) -> {
                    output.accept(ModItems.DOLL_MACHINE);
                    output.accept(ModItems.PURPLE_DOLL_GIFT_BOX);
                    output.accept(ModItems.GREEN_DOLL_GIFT_BOX);
                    output.accept(ModItems.YELLOW_DOLL_GIFT_BOX);
                    ModRegisterEvent.DOLL_ITEMS.forEach(output::accept);
                }).build());
    }
}
