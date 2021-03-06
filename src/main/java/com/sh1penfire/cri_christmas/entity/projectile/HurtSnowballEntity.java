package com.sh1penfire.cri_christmas.entity.projectile;

import com.google.common.base.MoreObjects;
import com.sh1penfire.cri_christmas.item.HurtSnowballItem;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class HurtSnowballEntity extends SnowballEntity {

    public HurtSnowballEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        //if the item is instanceof HurtSnowballItem
        if(getItem().getItem() instanceof HurtSnowballItem snowball && snowball.HANDLE_HIT) {
            //let the item handle it
            snowball.onEntityHit(entityHitResult, this);
            return;
        }

        //otherwise default to normal snowball behaviour... totaly normal
        Entity entity = entityHitResult.getEntity();
        int i = entity instanceof BlazeEntity ? 5 : 2;
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float)i);
        if (entity instanceof LivingEntity) {
            ((LivingEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 150, 2), MoreObjects.firstNonNull(entity, this));
        }
    }
}
