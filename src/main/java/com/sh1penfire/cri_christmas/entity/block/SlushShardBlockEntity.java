package com.sh1penfire.cri_christmas.entity.block;

import com.sh1penfire.cri_christmas.Christmas;
import com.sh1penfire.cri_christmas.registry.BlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class SlushShardBlockEntity extends BlockEntity {

    public SlushShardBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.SLUSH_SHARD_ENTITY, pos, state);
    }
}