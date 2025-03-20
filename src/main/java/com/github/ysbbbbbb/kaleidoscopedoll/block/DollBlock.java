package com.github.ysbbbbbb.kaleidoscopedoll.block;

import com.github.ysbbbbbb.kaleidoscopedoll.init.ModSounds;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DollBlock extends HorizontalDirectionalBlock {
    private static final MapCodec<DollBlock> CODEC = simpleCodec(prop -> new DollBlock());
    private static final VoxelShape SHAPE = Block.box(2.0d, 0.0d, 2.0d, 14.0d, 12.0d, 14.0d);

    public DollBlock() {
        super(Properties.of()
                .instrument(NoteBlockInstrument.BASEDRUM)
                .sound(SoundType.WOOL).strength(0f, 10f)
                .noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.SOUTH));
    }

    public static DollBlock getAuthorDoll() {
        return new DollBlock() {
            @Override
            public void appendHoverText(ItemStack pStack, Item.TooltipContext context, List<Component> list, TooltipFlag pFlag) {
                list.add(Component.translatable("tooltip.kaleidoscope_doll.doll.author").withStyle(ChatFormatting.DARK_GRAY));
            }
        };
    }

    @Override
    public InteractionResult useWithoutItem(BlockState pState, Level level, BlockPos pos, Player pPlayer, BlockHitResult pHit) {
        if (level instanceof ServerLevel serverLevel) {
            Vec3 notePos = Vec3.atBottomCenterOf(pos).add(
                    level.getRandom().nextFloat() / 2 - 0.25,
                    1 + level.getRandom().nextFloat() / 5,
                    level.getRandom().nextFloat() / 2 - 0.25
            );
            float color = level.getRandom().nextInt(4) / 24.0F;
            serverLevel.sendParticles(ParticleTypes.NOTE, notePos.x(), notePos.y(), notePos.z(), 0, color, 0, 0, 1);
            serverLevel.playSound(null, pos, ModSounds.DUCK_TOY, SoundSource.BLOCKS, 1.0f, level.random.nextFloat() * 0.5f + 0.75f);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public String getDescriptionId() {
        return "block.kaleidoscope_doll.doll";
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }
}
