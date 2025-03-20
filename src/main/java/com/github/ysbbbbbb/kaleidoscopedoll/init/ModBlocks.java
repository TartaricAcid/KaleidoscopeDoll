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

    public static DeferredBlock<Block> DOLL_MACHINE = BLOCKS.registerBlock("doll_machine", DollMachineBlock::new);
    public static DeferredBlock<Block> DOLL_AUTHOR_YSBB = BLOCKS.registerBlock("doll_0", DollBlock::getAuthorDoll);
    public static DeferredBlock<Block> DOLL_AUTHOR_943 = BLOCKS.registerBlock("doll_1", DollBlock::getAuthorDoll);
    public static DeferredBlock<Block> PURPLE_DOLL_GIFT_BOX = BLOCKS.registerBlock("purple_doll_gift_box", DollGiftBoxBlock::new);
    public static DeferredBlock<Block> GREEN_DOLL_GIFT_BOX = BLOCKS.registerBlock("green_doll_gift_box", DollGiftBoxBlock::new);
    public static DeferredBlock<Block> YELLOW_DOLL_GIFT_BOX = BLOCKS.registerBlock("yellow_doll_gift_box", DollGiftBoxBlock::new);
}
