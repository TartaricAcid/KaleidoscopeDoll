package com.github.ysbbbbbb.kaleidoscopedoll.block;

import com.github.ysbbbbbb.kaleidoscopedoll.config.GeneralConfig;
import com.github.ysbbbbbb.kaleidoscopedoll.init.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DollMachineBlock extends HorizontalDirectionalBlock {
    private static final MapCodec<DollMachineBlock> CODEC = simpleCodec(prop -> new DollMachineBlock());
    private static final TagKey<Item> DOLL_MACHINE_TOKENS = TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("doll_machine_tokens"));
    private static final VoxelShape SHAPE = Block.box(1.0d, 0.0d, 1.0d, 15.0d, 24.0d, 15.0d);
    private static final BooleanProperty LOTTERY_IN_PROGRESS = BooleanProperty.create("lottery_in_progress");

    public DollMachineBlock() {
        super(BlockBehaviour.Properties.of().ignitedByLava()
                .instrument(NoteBlockInstrument.BASS)
                .sound(SoundType.COPPER)
                .strength(8f, 10f)
                .lightLevel(s -> 2)
                .noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LOTTERY_IN_PROGRESS, false));
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        if (stack.is(DOLL_MACHINE_TOKENS) && !blockstate.getValue(LOTTERY_IN_PROGRESS)) {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            stack.shrink(1);
            world.setBlockAndUpdate(pos, blockstate.setValue(LOTTERY_IN_PROGRESS, true));
            world.playSound(null, pos, SoundEvents.LARGE_AMETHYST_BUD_PLACE, SoundSource.BLOCKS, 4f, 2f);
            if (world instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.WAX_ON, x + 0.5, y + 1, z + 0.5, 8, 0.5, 0.5, 0.5, 0.2);
                serverLevel.sendParticles(ParticleTypes.WAX_OFF, x + 0.5, y + 1, z + 0.5, 8, 0.5, 0.5, 0.5, 0.2);
                world.scheduleTick(pos, blockstate.getBlock(), 60);
            }
            return ItemInteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, blockstate, world, pos, entity, hand, hit);
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
        if (blockstate.getValue(LOTTERY_IN_PROGRESS)) {
            dropGiftBox(world, blockstate, pos, random);
        }
    }

    public void dropGiftBox(ServerLevel world, BlockState blockstate, BlockPos pos, RandomSource random) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        world.sendParticles(ParticleTypes.ANGRY_VILLAGER.getType(), x, y + 1.8, z, 10, 0.1, 0.1, 0.1, 0);
        world.playSound(null, x, y, z, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 1f, 1f);
        world.setBlockAndUpdate(pos, blockstate.setValue(LOTTERY_IN_PROGRESS, false));

        int yellowCount = GeneralConfig.YELLOW_DOLL_GIFT_BOX_WEIGHT.get();
        int greenCount = yellowCount + GeneralConfig.GREEN_DOLL_GIFT_BOX_WEIGHT.get();
        int totalCount = greenCount + GeneralConfig.PURPLE_DOLL_GIFT_BOX_WEIGHT.get();
        int count = random.nextInt(0, totalCount);

        ItemEntity item;
        if (count <= yellowCount) {
            item = new ItemEntity(world, x + 0.5, y + 1.8, z + 0.5, ModItems.YELLOW_DOLL_GIFT_BOX.get().getDefaultInstance());
        } else if (count <= greenCount) {
            item = new ItemEntity(world, x + 0.5, y + 1.8, z + 0.5, ModItems.GREEN_DOLL_GIFT_BOX.get().getDefaultInstance());
        } else {
            item = new ItemEntity(world, x + 0.5, y + 1.8, z + 0.5, ModItems.PURPLE_DOLL_GIFT_BOX.get().getDefaultInstance());
        }
        item.setPickUpDelay(10);
        world.addFreshEntity(item);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LOTTERY_IN_PROGRESS);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag pFlag) {
        tooltip.add(Component.translatable("tooltip.kaleidoscope_doll.doll_machine").withStyle(ChatFormatting.DARK_GRAY));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }
}
