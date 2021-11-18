package com.sh1penfire.cri_christmas.registry;

import com.sh1penfire.cri_christmas.Christmas;
import com.sh1penfire.cri_christmas.item.SnowballLauncherItem;
import com.sh1penfire.cri_christmas.item.WhipItem;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ChristmasItems {
    public static final Item WOOD_SNOWBALL_GUN = new SnowballLauncherItem(new Item.Settings().group(ItemGroup.COMBAT)){{

    }},

    CANDY_CANE = new WhipItem(ToolMaterials.WOOD, 5, 1f, new Item.Settings().group(ItemGroup.COMBAT)){{}},

    ICEBALL = new SnowballItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(16));

    public static void load() {
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "wood_snowball_gun"), WOOD_SNOWBALL_GUN);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "candy_cane"), CANDY_CANE);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "iceball"), ICEBALL);
    }
}
