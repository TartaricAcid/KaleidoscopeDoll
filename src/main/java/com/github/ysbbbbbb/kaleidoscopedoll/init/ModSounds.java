package com.github.ysbbbbbb.kaleidoscopedoll.init;

import com.github.ysbbbbbb.kaleidoscopedoll.KaleidoscopeDoll;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static SoundEvent DUCK_TOY = SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "block.duck_toy"), 16);

    public static void registerSounds() {
        Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.fromNamespaceAndPath(KaleidoscopeDoll.MOD_ID, "block.duck_toy"), DUCK_TOY);
    }
}