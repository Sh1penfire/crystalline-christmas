package com.sh1penfire.cri_christmas.item;

import com.sh1penfire.cri_christmas.entity.projectile.HurtSnowball;
import com.sh1penfire.cri_christmas.registry.ChristmasItems;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.*;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.particle.*;
import net.minecraft.predicate.entity.EntityEffectPredicate.EffectData;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class SnowballLauncher extends RangedWeaponItem {

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 4;
    }

    public final Predicate<ItemStack> SNOWBALL_PRED = (itemStack -> {
        return itemStack.getItem() instanceof SnowballItem;
    });

    public SnowballLauncher(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        boolean cMode = user.getAbilities().creativeMode;

        ItemStack snowballs = getSnowballs(user);
        if(snowballs == null) {
            if(cMode) snowballs = new ItemStack(ChristmasItems.ICEBALL);
            else return TypedActionResult.pass(itemStack);
        }

        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            for (int i = 0; i < 3; i++) {
                HurtSnowball snowballEntity = new HurtSnowball(world, user);
                snowballEntity.setItem(snowballs);
                snowballEntity.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 3F);
                world.spawnEntity(snowballEntity);
                if(!cMode) snowballs.decrement(1);
                if (snowballs.isEmpty() && !cMode) {
                    user.getInventory().removeOne(snowballs);
                    snowballs = getSnowballs(user);
                    if(snowballs == null) break;
                }
            }
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return SNOWBALL_PRED;
    }

    @Override
    public int getRange() {
        return 16;
    }

    @Nullable
    public ItemStack getSnowballs(PlayerEntity playerEntity){
        for(int i = 0; i < playerEntity.getInventory().size(); ++i) {
            ItemStack itemStack = playerEntity.getInventory().getStack(i);
            if (SNOWBALL_PRED.test(itemStack)) {
                return itemStack;
            }
        }
        return null;
    }
}
