package com.sh1penfire.cri_christmas.item;

import com.sh1penfire.cri_christmas.content.AmmoMap;
import com.sh1penfire.cri_christmas.registry.ChristmasItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.SnowballItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class SnowballLauncherItem extends RangedWeaponItem {

    public AmmoMap AMMO_MAP = new AmmoMap();

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 4;
    }

    public final Predicate<ItemStack> SNOWBALL_PRED = (itemStack -> {
        return itemStack.getItem() instanceof SnowballItem;
    });

    public SnowballLauncherItem(Settings settings) {
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
                Entity ammoEntity = AMMO_MAP.getAmmo(snowballs.getItem()).get(user, world);
                if(ammoEntity instanceof SnowballEntity snowballEntity){
                    snowballEntity.setItem(snowballs);
                    snowballEntity.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 3F);
                    world.spawnEntity(snowballEntity);
                }

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
