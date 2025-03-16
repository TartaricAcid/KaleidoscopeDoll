package com.github.ysbbbbbb.kaleidoscopedoll.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class DollItem extends BlockItem {
    public DollItem(Block block) {
        super(block, new FabricItemSettings().equipmentSlot(s -> EquipmentSlot.HEAD));
    }
}
