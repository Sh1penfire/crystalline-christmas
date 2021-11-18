package com.sh1penfire.cri_christmas.entity.projectile;

import com.sh1penfire.cri_christmas.Christmas;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.apache.logging.log4j.Level;

//will become @Depreciated eventualy
public class SlimeGelEntity extends HurtSnowballEntity{

    public double bounces = 10;

    public SlimeGelEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    @Override
    public void handleStatus(byte status) {
        if (status == 3 || status == 0) {
            playSound(SoundEvents.BLOCK_SLIME_BLOCK_BREAK, 1, (float) Math.random() * 0.55f + 0.85f);
            ParticleEffect particleEffect = ParticleTypes.ITEM_SLIME;
            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }
    @Override
    protected void onCollision(HitResult hitResult) {
        HitResult.Type type = hitResult.getType();
        if (type == HitResult.Type.ENTITY) {
            this.onEntityHit((EntityHitResult)hitResult);
        } else if (type == HitResult.Type.BLOCK) {
            this.onBlockHit((BlockHitResult)hitResult);
        }
        if (type != HitResult.Type.MISS) {
            this.emitGameEvent(GameEvent.PROJECTILE_LAND, this.getOwner());
        }

        if (!this.world.isClient) {
            if(bounces < 1 || hitResult.getType() != HitResult.Type.BLOCK){
                this.discard();
                this.world.sendEntityStatus(this, (byte)3);
                return;
            }
            else this.world.sendEntityStatus(this, (byte)0);
        }
    }

    //TODO: Make bouncing projectiles not clip through blocks.
    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        bounces -= 1;
        Vec3d offset = blockHitResult.getPos().subtract(getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getY());
        Vec3d bounceVel = new Vec3d(
                offset.x == 0 ? getVelocity().x : getVelocity().x * (offset.x >= 0 ? -1 : 1),
                offset.y == 0 ? getVelocity().y : getVelocity().y * (offset.y >= 0 ? -1 : 1),
                offset.z == 0 ? getVelocity().z : getVelocity().z * (offset.z >= 0 ? -1 : 1)
        );

        Christmas.LOGGER.info(String.format("placeholder:{} velocity:{} block offset:{}", bounceVel.toString(), getVelocity(), offset.toString()));

        setPosition(getPos().subtract(getVelocity()));
        setVelocity(bounceVel);
        velocityModified = true;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 0);

        //there's probably a better way to do this, but I'm unsure how. For now have probably performance heavy vector calculation.
        Vec3d knockbackVector = Vec3d.ZERO.add(entity.getPos()).subtract(prevX, prevY, prevZ).normalize().multiply(getKnockback()).add(0, getKnockback()/6, 0);
        entity.addVelocity(knockbackVector.x, knockbackVector.y, knockbackVector.z);
        playSound(SoundEvents.BLOCK_SLIME_BLOCK_BREAK, 1, (float) Math.random() * 0.55f + 0.85f);
    }

    public double getKnockback(){
        return 0.75f;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        bounces = nbt.getDouble("bounces");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putDouble("bounces", bounces);
        return super.writeNbt(nbt);
    }
}
