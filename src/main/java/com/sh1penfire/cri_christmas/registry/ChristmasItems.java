package com.sh1penfire.cri_christmas.registry;

import com.sh1penfire.cri_christmas.Christmas;
import com.sh1penfire.cri_christmas.entity.projectile.*;
import com.sh1penfire.cri_christmas.item.*;
import com.sh1penfire.cri_christmas.util.interfaces.Prov;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.*;
import net.minecraft.item.Item.Settings;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static com.sh1penfire.cri_christmas.content.CArmorMaterial.FROST;

public class ChristmasItems {

    public static List<ItemBlockEntry> MODDEDBLOCK_ITEMS = new ArrayList<ItemBlockEntry>();
    public static List<ItemEntry> MOD_ITEMS = new ArrayList<ItemEntry>();
    //slush weapons
    public static final SnowballLauncherItem WOOD_SNOWBALL_GUN = new SnowballLauncherItem(new Item.Settings().group(ItemGroup.COMBAT)){{

    }};

    public static final Item

    CANDY_CANE = new WhipItem(ToolMaterials.WOOD, 5, 1f, new Item.Settings().group(ItemGroup.COMBAT)){{}},

    CANDY_CANE_LANCE = new SwordItem(ToolMaterials.WOOD, 5, 1f, new Item.Settings().group(ItemGroup.COMBAT)){{}},

    //Slush Blaster Ammo
    ICEBALL = new SnowballItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(16)),

    SLIMY_GEL = new SlimeGelItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(64)),

    CREAMY_GEL = new CreamyGelItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(64));

    //resources
    public static Item SLUSH_SHARD = registerItem("slush_shard", new Item(new Item.Settings().group(ItemGroup.MATERIALS)));

    //Armors
    public static ArmorItem FROST_BOOTS = new ArmorItem(FROST, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)){{

    }},

    FROST_LEGGINGS = new ArmorItem(FROST, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)){{

    }},

    FROST_VEST = new ArmorItem(FROST, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)){{

    }},

    FROST_HELM = new ArmorItem(FROST, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)){{

    }};

    public static Item

    //candy foods

    CANDYCANE_RED = new Item(new Item.Settings().group(ItemGroup.FOOD).maxCount(64).food(ChristmasFoodComponents.CANDYCANE)){{

    }},

    CANDYCANE_YELLOW = new Item(new Item.Settings().group(ItemGroup.FOOD).maxCount(64).food(ChristmasFoodComponents.CANDYCANE)){{

    }},

    CANDYCANE_GREEN = new Item(new Item.Settings().group(ItemGroup.FOOD).maxCount(64).food(ChristmasFoodComponents.CANDYCANE)){{

    }},

    CANDI = new Item(new Item.Settings().group(ItemGroup.FOOD).maxCount(64).food(ChristmasFoodComponents.CANDYCANE)){{

    }};

    //block items
    public static final BlockItem MILK_BLOCK = new BlockItem(ChristmasBlocks.MILK_BLOCK, new Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "wood_snowball_gun"), WOOD_SNOWBALL_GUN);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "candy_cane"), CANDY_CANE);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "candy_cane_lance"), CANDY_CANE_LANCE);

        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "iceball"), ICEBALL);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "slimy_gel"), SLIMY_GEL);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "creamy_gel"), CREAMY_GEL);
        //candy foods

        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "candycane_red"), CANDYCANE_RED);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "candycane_green"), CANDYCANE_GREEN);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "candycane_yellow"), CANDYCANE_YELLOW);

        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "candi"), CANDI);

        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "frost_boots"), FROST_BOOTS);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "frost_leggings"), FROST_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "frost_vest"), FROST_VEST);
        Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, "frost_helm"), FROST_HELM);

        //items
        MOD_ITEMS.forEach(i -> Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, i.registerName), i.item));

        //block items
        MODDEDBLOCK_ITEMS.forEach(i -> Registry.register(Registry.ITEM, new Identifier(Christmas.MOD_ID, i.registerName), i.blockItem));


        //blaster ammoes

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

        WOOD_SNOWBALL_GUN.AMMO_MAP.putAmmo(CREAMY_GEL, new Prov<>() {
            @Override
            public Entity get(PlayerEntity user, World world) {
                return new FireGelEntity(world, user);
            }
        });

        WOOD_SNOWBALL_GUN.AMMO_MAP.putAmmo(Items.FIRE_CHARGE, new Prov<>() {
            @Override
            public Entity get(PlayerEntity user, World world) {
                SmallFireballEntity fireball = new SmallFireballEntity(world, user, 0, 0, 0);
                return fireball;
            }
        });
    }

    public static class ItemBlockEntry{
        public String registerName;
        public BlockItem blockItem;
        public ItemBlockEntry(String path, BlockItem item){
            registerName = path;
            blockItem = item;
        }
    }

    public static class ItemEntry{
        public String registerName;
        public Item item;
        public ItemEntry(String path, Item item){
            this.registerName = path;
            this.item = item;
        }
    }

    public static Item registerItem(String registerPath, Item item){
        MOD_ITEMS.add(new ItemEntry(registerPath, item));
        return item;
    }

    //does nothing currently, planning on making it auto add to a specific specified ammo map
    public static Item registerGelItem(String registerPath, Item item){
        MOD_ITEMS.add(new ItemEntry(registerPath, item));
        return item;
    }
}
