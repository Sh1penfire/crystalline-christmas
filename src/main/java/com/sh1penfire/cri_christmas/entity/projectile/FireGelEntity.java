package com.sh1penfire.cri_christmas.entity.projectile;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class FireGelEntity extends HurtSnowballEntity{
    public FireGelEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        World world = getEntityWorld();
        BlockPos blockPos = getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);

        super.onBlockHit(blockHitResult);
        if (!CampfireBlock.canBeLit(blockState) && !CandleBlock.canBeLit(blockState) && !CandleCakeBlock.canBeLit(blockState)) {
            blockPos = blockHitResult.getBlockPos().offset(blockHitResult.getSide());
            if (AbstractFireBlock.canPlaceAt(world, blockPos, blockHitResult.getSide())) {
                playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1, 1);
                world.setBlockState(blockPos, AbstractFireBlock.getState(world, blockPos));
            }
        } else {
            playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1, 1);
            world.setBlockState(blockPos, (BlockState)blockState.with(Properties.LIT, true));
        }
    }
}
