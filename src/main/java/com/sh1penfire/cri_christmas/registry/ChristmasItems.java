package com.sh1penfire.cri_christmas.registry;

import com.sh1penfire.cri_christmas.Christmas;
import com.sh1penfire.cri_christmas.entity.projectile.HurtSnowballEntity;
import com.sh1penfire.cri_christmas.entity.projectile.SlimeGelEntity;
import com.sh1penfire.cri_christmas.item.ChristmasFoodComponents;
import com.sh1penfire.cri_christmas.item.SnowballLauncherItem;
import com.sh1penfire.cri_christmas.item.WhipItem;
import com.sh1penfire.cri_christmas.util.interfaces.Prov;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ChristmasItems {
    //slush weapons
    public static final SnowballLauncherItem WOOD_SNOWBALL_GUN = new SnowballLauncherItem(new Item.Settings().group(ItemGroup.COMBAT)){{

    }};

    public static final Item

    CANDY_CANE = new WhipItem(ToolMaterials.WOOD, 5, 1f, new Item.Settings().group(ItemGroup.COMBAT)){{}},
    
    //Slush Blaster Ammo
    ICEBALL = new SnowballItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(16)),

    SLIMY_GEL = new SnowballItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(64));


    public static Item

    //candy foods

    CANDYCANE = new Item(new Item.Settings().group(ItemGroup.FOOD).maxCount(64).food(ChristmasFoodComponents.CANDYCANE)){{

    }},

    CANDI = new Item(new Item.Settings().group(ItemGroup.FOOD).maxCount(64).food(ChristmasFoodComponents.CANDYCANE)){{

    }};

    public static void load() {
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "wood_snowball_gun"), WOOD_SNOWBALL_GUN);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "candy_cane"), CANDY_CANE);

        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "iceball"), ICEBALL);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "slimy_gel"), SLIMY_GEL);
        //candy foods

        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "candycane"), CANDYCANE);

        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "candi"), CANDI);

        WOOD_SNOWBALL_GUN.AMMO_MAP.putAmmo(Items.SNOWBALL, new Prov<>() {
            @Override
            public Entity get(PlayerEntity user, World world) {
                return new SnowballEntity(world, user);
            }
        });

        WOOD_SNOWBALL_GUN.AMMO_MAP.putAmmo(ICEBALL, new Prov<>() {
            @Override
            public Entity get(PlayerEntity user, World world) {
                return new HurtSnowballEntity(world, user);
            }
        });

        WOOD_SNOWBALL_GUN.AMMO_MAP.putAmmo(SLIMY_GEL, new Prov<>() {
            @Override
            public Entity get(PlayerEntity user, World world) {
                return new SlimeGelEntity(world, user);
            }
        });
    }
}
