package com.sh1penfire.cri_christmas.registry;

import com.sh1penfire.cri_christmas.Christmas;
import com.sh1penfire.cri_christmas.item.SnowballLauncher;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.*;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ChristmasItems {
    public static final Item WOOD_SNOWBALL_GUN = new SnowballLauncher(new Item.Settings().group(ItemGroup.COMBAT)){{

    }},

    ICEBALL = new SnowballItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(16));

    public static void load() {
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "wood_snowball_gun"), WOOD_SNOWBALL_GUN);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "iceball"), ICEBALL);
    }
}
