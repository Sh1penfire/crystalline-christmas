package com.sh1penfire.cri_christmas.item;

import com.sh1penfire.cri_christmas.entity.projectile.HurtSnowballEntity;
import com.sh1penfire.cri_christmas.entity.projectile.SlimeGelEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

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

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        if (!world.isClient) {
            SlimeGelEntity snowballEntity = new SlimeGelEntity(world, user);
            snowballEntity.setItem(itemStack);
            snowballEntity.setProperties(user, user.getPitch(), user.getYaw(), 0.0f, 1.5f, 1.0f);
            world.spawnEntity(snowballEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
