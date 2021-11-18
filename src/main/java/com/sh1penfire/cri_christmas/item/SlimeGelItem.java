package com.sh1penfire.cri_christmas.item;

import com.sh1penfire.cri_christmas.entity.projectile.HurtSnowballEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;

public class SlimeGelItem extends HurtSnowballItem{
    public double KNOCKBACK = 3;

    public SlimeGelItem(Settings settings) {
        super(settings);
        HANDLE_HIT = true;
    }

    @Override
    public void onEntityHit(EntityHitResult entityHitResult, HurtSnowballEntity snowballEntity) {
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.thrownProjectile(snowballEntity, snowballEntity.getOwner()), 0);

        //there's probably a better way to do this, but I'm unsure how. For now have probably performance heavy vector calculation.
        Vec3d knockbackVector = new Vec3d(snowballEntity.prevX, snowballEntity.prevY, snowballEntity.prevZ).subtract(entity.getPos()).normalize().multiply(KNOCKBACK);
        entity.addVelocity(knockbackVector.x, knockbackVector.y, knockbackVector.z);
    }
}
