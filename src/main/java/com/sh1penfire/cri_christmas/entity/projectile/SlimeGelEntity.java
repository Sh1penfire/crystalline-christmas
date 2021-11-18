package com.sh1penfire.cri_christmas.entity.projectile;

import net.minecraft.client.sound.Sound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

//will become @Depreciated eventualy
public class SlimeGelEntity extends HurtSnowballEntity{
    public SlimeGelEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    @Override
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = ParticleTypes.ITEM_SLIME;
            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 0);

        //there's probably a better way to do this, but I'm unsure how. For now have probably performance heavy vector calculation.
        Vec3d knockbackVector = new Vec3d(entity.getX(), entity.getY(), entity.getZ()).subtract(this.prevX, this.prevY, this.prevZ).normalize().multiply(getKnockback());
        entity.addVelocity(knockbackVector.x, knockbackVector.y, knockbackVector.z);
        playSound(SoundEvents.BLOCK_SLIME_BLOCK_BREAK, 1, (float) Math.random() * 0.55f + 0.85f);
    }

    public double getKnockback(){
        return 0.75f;
    }
}
