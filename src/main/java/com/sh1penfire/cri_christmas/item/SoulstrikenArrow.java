package com.sh1penfire.cri_christmas.item;

import com.sh1penfire.cri_christmas.entity.CEntityType;
import com.sh1penfire.cri_christmas.entity.projectile.SoulstrikenArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SoulstrikenArrow extends ArrowItem {
    public SoulstrikenArrow(Settings settings) {
        super(settings);
    }

    @Override

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        SoulstrikenArrowEntity arrowEntity = new SoulstrikenArrowEntity(CEntityType.SOULSTRIKEN_ARROW, world);
        return arrowEntity;
    }
}
