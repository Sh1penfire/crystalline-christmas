package com.sh1penfire.cri_christmas.block;

import com.google.common.collect.ImmutableMap;
import com.sh1penfire.cri_christmas.registry.ChristmasBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Function;

public class SlushShard extends Block implements BlockEntityProvider {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = Properties.FACING;
    protected final VoxelShape NORTH_SHAPE;
    protected final VoxelShape SOUTH_SHAPE;
    protected final VoxelShape EAST_SHAPE;
    protected final VoxelShape WEST_SHAPE;
    protected final VoxelShape UP_SHAPE;
    protected final VoxelShape DOWN_SHAPE;

    public SlushShard(int height, int xzOffset, AbstractBlock.Settings settings) {
        super(settings);

        this.setDefaultState((this.getDefaultState().with(WATERLOGGED, false)).with(FACING, Direction.UP));
        this.UP_SHAPE = Block.createCuboidShape(xzOffset, 0.0, xzOffset, 16 - xzOffset, height, 16 - xzOffset);
        this.DOWN_SHAPE = Block.createCuboidShape(xzOffset, 16 - height, xzOffset, 16 - xzOffset, 16.0, 16 - xzOffset);
        this.NORTH_SHAPE = Block.createCuboidShape(xzOffset, xzOffset, 16 - height, 16 - xzOffset, 16 - xzOffset, 16.0);
        this.SOUTH_SHAPE = Block.createCuboidShape(xzOffset, xzOffset, 0.0, 16 - xzOffset, 16 - xzOffset, height);
        this.EAST_SHAPE = Block.createCuboidShape(0.0, xzOffset, xzOffset, height, 16 - xzOffset, 16 - xzOffset);
        this.WEST_SHAPE = Block.createCuboidShape(16 - height, xzOffset, xzOffset, 16.0, 16 - xzOffset, 16 - xzOffset);
    }



    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!canGrowIn(state) || random.nextInt(5) != 0) {
            return;
        }
        Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
        Block block = null;
        if (state.isOf(ChristmasBlocks.CRYSTALLINE_SLUSH_SMALL)) {
            block = ChristmasBlocks.CRYSTALLINE_SLUSH_MEDIUM;
        } else if (state.isOf(ChristmasBlocks.CRYSTALLINE_SLUSH_MEDIUM)) {
            block = ChristmasBlocks.CRYSTALLINE_SLUSH_LARGE;
        } else if (state.isOf(ChristmasBlocks.CRYSTALLINE_SLUSH_LARGE)) {
            block = ChristmasBlocks.SLUSH_SHARD;
        }
        if (block != null) {
            BlockState blockState2 = (block.getDefaultState().with(SlushShard.FACING, direction)).with(SlushShard.WATERLOGGED, state.getFluidState().getFluid() == Fluids.WATER);
            world.setBlockState(pos, blockState2);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        switch (direction) {
            case NORTH: {
                return this.NORTH_SHAPE;
            }
            case SOUTH: {
                return this.SOUTH_SHAPE;
            }
            case EAST: {
                return this.EAST_SHAPE;
            }
            case WEST: {
                return this.WEST_SHAPE;
            }
            case DOWN: {
                return this.DOWN_SHAPE;
            }
        }
        return this.UP_SHAPE;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, direction);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED).booleanValue()) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction == state.get(FACING).getOpposite() && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World worldAccess = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        return this.getDefaultState().with(WATERLOGGED, worldAccess.getFluidState(blockPos).getFluid() == Fluids.WATER).with(FACING, ctx.getSide());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED).booleanValue()) {
            return Fluids.WATER.getStill(false);
        }
        return Fluids.EMPTY.getDefaultState();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }


    public static boolean canGrowIn(BlockState state) {
        return (state.isAir() || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
