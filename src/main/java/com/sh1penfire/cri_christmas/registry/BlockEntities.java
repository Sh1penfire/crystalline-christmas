package com.sh1penfire.cri_christmas.registry;

import com.sh1penfire.cri_christmas.entity.block.SlushShardBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder.Factory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

public class BlockEntities {
    public static BlockEntityType<SlushShardBlockEntity> SLUSH_SHARD_ENTITY;

    public void onInitialize() {
        SLUSH_SHARD_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "crymas:slush_shard_block_entity", FabricBlockEntityTypeBuilder.create(SlushShardBlockEntity::new, ChristmasBlocks.CRYSTALLINE_SLUSH_SMALL).build(null));
    }
}
