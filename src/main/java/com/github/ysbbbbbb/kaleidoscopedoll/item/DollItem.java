package com.github.ysbbbbbb.kaleidoscopedoll.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class DollItem extends BlockItem {
    public DollItem(Block block) {
        super(block, new Item.Properties().equipmentSlot((entity, item) -> EquipmentSlot.HEAD));
    }
}
