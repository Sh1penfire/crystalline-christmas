package com.sh1penfire.cri_christmas.entity;

import com.sh1penfire.cri_christmas.entity.projectile.SoulstrikenArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class CEntityType {
    public static final EntityType<SoulstrikenArrowEntity> SOULSTRIKEN_ARROW = register("soulstriken_arrow", EntityType.Builder.create(new EntityType.EntityFactory(){
        @Override
        public Entity create(EntityType type, World world) {
            return new SoulstrikenArrowEntity(type, world);
        }
    }, SpawnGroup.MISC).setDimensions(0.5f, 0.5f).maxTrackingRange(4).trackingTickInterval(20));

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registry.ENTITY_TYPE, id, type.build(id));
    }
}

