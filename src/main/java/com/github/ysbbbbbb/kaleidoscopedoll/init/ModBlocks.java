package com.github.ysbbbbbb.kaleidoscopedoll.init;

import com.github.ysbbbbbb.kaleidoscopedoll.KaleidoscopeDoll;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollBlock;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollGiftBoxBlock;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollMachineBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(KaleidoscopeDoll.MOD_ID);

    public static DeferredBlock<Block> DOLL_MACHINE = BLOCKS.register("doll_machine", DollMachineBlock::new);
    public static DeferredBlock<Block> PURPLE_DOLL_GIFT_BOX = BLOCKS.register("purple_doll_gift_box", DollGiftBoxBlock::new);
    public static DeferredBlock<Block> GREEN_DOLL_GIFT_BOX = BLOCKS.register("green_doll_gift_box", DollGiftBoxBlock::new);
    public static DeferredBlock<Block> YELLOW_DOLL_GIFT_BOX = BLOCKS.register("yellow_doll_gift_box", DollGiftBoxBlock::new);
}
