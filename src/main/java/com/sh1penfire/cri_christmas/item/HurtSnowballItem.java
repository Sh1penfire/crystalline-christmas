package com.sh1penfire.cri_christmas.item;

import com.sh1penfire.cri_christmas.entity.projectile.HurtSnowballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SnowballItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class HurtSnowballItem extends SnowballItem {
    public boolean HANDLE_HIT = false;

    public HurtSnowballItem(Settings settings) {
        super(settings);
    }

    public void onEntityHit(EntityHitResult entityHitResult, HurtSnowballEntity snowballEntity){
        //left empty for you to interpret what you could do, like fill the Endless Void that is depression and Anxiety, or do something better with your time than looking at this.
        //note that THIS WON'T WORK AAAA
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        if (!world.isClient) {
            HurtSnowballEntity snowballEntity = new HurtSnowballEntity(world, user);
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
