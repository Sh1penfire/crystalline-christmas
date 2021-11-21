package com.sh1penfire.cri_christmas.entity.projectile;

import com.google.common.base.MoreObjects;
import com.sh1penfire.cri_christmas.entity.CEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class SoulstrikenArrowEntity extends PersistentProjectileEntity {

    public SoulstrikenArrowEntity(EntityType<? extends SoulstrikenArrowEntity> entityType, World world) {
        super((EntityType<? extends PersistentProjectileEntity>)entityType, world);
    }

    public SoulstrikenArrowEntity(World world, LivingEntity owner) {
        super(CEntityType.SOULSTRIKEN_ARROW, owner, world);
    }

    public SoulstrikenArrowEntity(World world, double x, double y, double z) {
        super(CEntityType.SOULSTRIKEN_ARROW, x, y, z, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.world.isClient && !this.inGround) {
            this.world.addParticle(ParticleTypes.SOUL, this.getX(), this.getY(), this.getZ(), getVelocity().x * 0.3f, 1, getVelocity().z * 0.3f);
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(Items.SPECTRAL_ARROW);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.WITHER, 50, 0);
        target.addStatusEffect(statusEffectInstance, this.getEffectCause());
    }
}
