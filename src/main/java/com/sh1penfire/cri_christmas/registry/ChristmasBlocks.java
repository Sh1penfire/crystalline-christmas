package com.sh1penfire.cri_christmas.registry;

import com.sh1penfire.cri_christmas.Christmas;
import com.sh1penfire.cri_christmas.block.SlushShard;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

import static com.sh1penfire.cri_christmas.registry.ChristmasItems.MODDEDBLOCK_ITEMS;

public class ChristmasBlocks {

    public static final List<BlockEntry> MOD_BLOCKS = new ArrayList<BlockEntry>();

    //blocks
    public static final Block MILK_BLOCK = registerBlock("milk_block",
        new Block(FabricBlockSettings.of(Material.SNOW_BLOCK).requiresTool().strength(0.2f).sounds(BlockSoundGroup.SNOW)){{

        }},
        defaults()
    ),

    SLUSH_BLOCK = registerBlock("slush_block",
        new Block(FabricBlockSettings.of(Material.SNOW_BLOCK).requiresTool().strength(0.2f).sounds(BlockSoundGroup.SNOW)){{

        }},
        defaults()
    ),

    SLUSH_SHARD = registerBlock("slush_shards", new SlushShard(7, 3, FabricBlockSettings.of(Material.AMETHYST).breakByHand(true).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().ticksRandomly()), defaults()),

    CRYSTALLINE_SLUSH_LARGE = registerBlock("crystalline_slush_large", new SlushShard(5, 3, FabricBlockSettings.of(Material.AMETHYST).breakByHand(true).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().ticksRandomly()), defaults()),

    CRYSTALLINE_SLUSH_MEDIUM = registerBlock("crystalline_slush_medium", new SlushShard(4, 3, FabricBlockSettings.of(Material.AMETHYST).breakByHand(true).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().ticksRandomly()), defaults()),

    CRYSTALLINE_SLUSH_SMALL = registerBlock("crystalline_slush_small", new SlushShard(3, 4, FabricBlockSettings.of(Material.AMETHYST).breakByHand(true).breakByHand(true).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool().ticksRandomly()), defaults()),

    CANDYITE = registerBlock("candyite_ore",
            new Block(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(3.0f, 3.0f)),
            defaults()
    ),

    DEEPSLATE_CANDYITE = registerBlock("deepslate_candyite_ore",
            new OreBlock(FabricBlockSettings.copyOf(CANDYITE)),
            defaults()
    ),

    CANDYCANE_BLOCK_RED = registerBlock("candycane_block_red",
        new Block(FabricBlockSettings.of(Material.WOOD).breakByHand(true).hardness(1.25f).resistance(15).sounds(BlockSoundGroup.BONE)),
        defaults()
    ),

    CANDYCANE_BLOCK_GREEN = registerBlock("candycane_block_green",
        new Block(FabricBlockSettings.of(Material.WOOL).breakByHand(true).hardness(1.25f).resistance(15).sounds(BlockSoundGroup.BONE)),
        defaults()
    ),

    CANDYCANE_BLOCK_YELLOW = registerBlock("candycane_block_yellow",
        new Block(FabricBlockSettings.of(Material.WOOL).breakByHand(true).hardness(1.25f).resistance(15).sounds(BlockSoundGroup.BONE)),
        defaults()
    );

    public static void registerBlocks(){
        MOD_BLOCKS.forEach(b -> {
            Registry.register(Registry.BLOCK, new Identifier(Christmas.MOD_ID, b.registerName), b.blockEntry);

        });
    }

    public static Item.Settings defaults(){
        return new Item.Settings().group(ItemGroup.BUILDING_BLOCKS);
    }

    public static Block registerBlock(String registerPath, Block block, Item.Settings itemSettings){
        BlockItem blockItem = new BlockItem(block, itemSettings);

        MOD_BLOCKS.add(new BlockEntry(registerPath, block, itemSettings));
        MODDEDBLOCK_ITEMS.add(new ChristmasItems.ItemBlockEntry(registerPath, blockItem));
        return block;
    }

    public static class BlockEntry{
        public Block blockEntry;
        public Item itemEntry;
        public String registerName;
        public BlockEntry(String name, Block entry, Item.Settings itemSetings){
            blockEntry = entry;
            itemEntry = new BlockItem(blockEntry, itemSetings);
            registerName = name;
        }
    }
}
