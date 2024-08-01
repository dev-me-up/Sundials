package com.github.devmeup.sundials.block;

import com.github.devmeup.sundials.references.ModTags;
import com.github.devmeup.sundials.util.TimeOfDayChecker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class SundialBlock extends Block implements Waterloggable {
    private static final DirectionProperty FACING = Properties.FACING;
    private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public SundialBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        String timeOfDay;
        timeOfDay = displayTime(world.getTimeOfDay());

        if (world.isClient()) {
            return ActionResult.CONSUME;
        }
        if (world.getDimension().hasFixedTime()) {
            player.sendMessage(Text.literal("You are unable to tell the time."));
        } else {
            if (hasSkyAccess(world, pos)) {
                player.sendMessage(Text.literal(timeOfDay));
            } else {
                player.sendMessage(Text.literal("You are unable to tell the time."));
            }
        }
        return ActionResult.SUCCESS;
    }

    protected boolean hasSkyAccess(World world, BlockPos pos) {
        for (int i = 0; i <= pos.getY() + world.getHeight(); i++) {
            BlockState state = world.getBlockState(pos.up(i));
            if (i != 0) { // Ignore the initial sundial block
                if (!(isSunlightTraversableBlocks(state) || isSunlightTraversableFluids(state) || state.isAir())) {
                    return false;
                }
            }
        }
        return true;
    }

    private String displayTime(long timeOfDayInTicks) {
        return TimeOfDayChecker.getTimeOfDayString(timeOfDayInTicks);
    }

    private boolean isSunlightTraversableBlocks(BlockState state) {
        return state.isIn(ModTags.Blocks.SUNLIGHT_TRAVERSABLE_BLOCKS);
    }

    private boolean isSunlightTraversableFluids(BlockState state) {
        return state.getFluidState().isIn(ModTags.Fluids.SUNLIGHT_TRAVERSABLE_FLUIDS);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING,rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.18F, 0F, 0.18F, 0.82F, 1.0F, 0.82F);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

}
