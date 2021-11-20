package com.sh1penfire.cri_christmas.content;

import com.sh1penfire.cri_christmas.util.interfaces.Prov;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Arrays;

public class AmmoMap {
    private Item[] AMMO_MAP = new Item[]{

    };

    private Prov<PlayerEntity, World, Entity>[] ENTITY_MAP = new Prov[]{};

    public void putAmmo(Item ammoProvider, Prov<PlayerEntity, World, Entity> entityProvider){
        AMMO_MAP = Arrays.copyOf(AMMO_MAP, AMMO_MAP.length + 1);
        ENTITY_MAP = Arrays.copyOf(ENTITY_MAP, ENTITY_MAP.length + 1);

        AMMO_MAP[AMMO_MAP.length - 1] = ammoProvider;
        ENTITY_MAP[ENTITY_MAP.length - 1] = entityProvider;
    }

    public Prov<PlayerEntity, World, Entity> getAmmo(Item ammo){
        for (int i = 0; i < AMMO_MAP.length; i++) {
            if(AMMO_MAP[i] == ammo) return ENTITY_MAP[i];
        }
        return null;
    };
}
