package com.sh1penfire.cri_christmas.content;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class CArmorMaterial implements ArmorMaterial {
    //public final static CArmorMaterial frost = new CArmorMaterial();
    private final int[] durability, armorValue;

    private final float toughness, KR;

    private final int enchantability;
    private final SoundEvent equipSound;
    public final String name;
    private final Ingredient repairIng;

    public CArmorMaterial(int[] durability, int[] armorValue, float toughness, float KR, int enchantability, SoundEvent equipSound, String name, Ingredient repairIngrediant){
        this.durability = durability;
        this.armorValue = armorValue;
        this.toughness = toughness;
        this.KR = KR;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.name = name;
        repairIng = repairIngrediant;
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return durability[slot.getEntitySlotId()];
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return armorValue[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
