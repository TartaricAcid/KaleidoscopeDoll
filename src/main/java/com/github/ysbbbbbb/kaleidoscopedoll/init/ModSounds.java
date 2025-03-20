package com.github.ysbbbbbb.kaleidoscopedoll.init;

import com.github.ysbbbbbb.kaleidoscopedoll.KaleidoscopeDoll;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, KaleidoscopeDoll.MOD_ID);

    public static Supplier<SoundEvent> DUCK_TOY = SOUND_EVENTS.register("block.duck_toy",
            () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "block.duck_toy"), 16));
}
