package com.github.ysbbbbbb.kaleidoscopedoll.init;

import com.github.ysbbbbbb.kaleidoscopedoll.KaleidoscopeDoll;
import com.github.ysbbbbbb.kaleidoscopedoll.event.ModRegisterEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KaleidoscopeDoll.MOD_ID);
    private static final ResourceLocation ICON_ID = new ResourceLocation(KaleidoscopeDoll.MOD_ID, "doll_52");

    public static RegistryObject<CreativeModeTab> DOLL_TAB = TABS.register("doll", () -> CreativeModeTab.builder()
            .title(Component.translatable("item_group.kaleidoscope_doll.doll.name"))
            .icon(() -> ForgeRegistries.ITEMS.getValue(ICON_ID).getDefaultInstance())
            .displayItems((par, output) -> {
                output.accept(ModItems.DOLL_MACHINE.get());
                output.accept(ModItems.PURPLE_DOLL_GIFT_BOX.get());
                output.accept(ModItems.GREEN_DOLL_GIFT_BOX.get());
                output.accept(ModItems.YELLOW_DOLL_GIFT_BOX.get());
                ModRegisterEvent.DOLL_ITEMS.forEach(output::accept);
            }).build());
}
