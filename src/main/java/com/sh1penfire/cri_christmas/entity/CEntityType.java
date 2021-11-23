package com.sh1penfire.cri_christmas.entity;

import com.sh1penfire.cri_christmas.entity.projectile.SoulstrikenArrowEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.apache.http.client.entity.EntityBuilder;

public class CEntityType {
    //public static final EntityType<SoulstrikenArrowEntity> SHARK_TOOTH_ARROW = register(EntityType.Builder.create(SoulstrikenArrowEntity::new, SpawnGroup.MISC).sized(0.5F, 0.5F).setCustomClientFactory(SoulstrikenArrowEntity::new), "shark_tooth_arrow");

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registry.ENTITY_TYPE, id, type.build(id));
    }

    public static void bake(){

    }

}