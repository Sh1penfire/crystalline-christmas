package com.sh1penfire.cri_christmas.registry;

import com.sh1penfire.cri_christmas.Christmas;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ChristmasItems {
    public static final Item WOOD_SNOWBALL_GUN = new Item(new Item.Settings().group(ItemGroup.COMBAT)){{

    }};

    public static void load() {
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "wood_snowball_gun"), WOOD_SNOWBALL_GUN);
    }
}
