package com.sh1penfire.cri_christmas.registry;

import com.sh1penfire.cri_christmas.Christmas;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

import static com.sh1penfire.cri_christmas.registry.ChristmasItems.MODDEDBLOCK_ITEMS;

public class ChristmasBlocks {

    public static final List<BlockEntry> MOD_BLOCKS = new ArrayList<BlockEntry>();

    //blocks
    public static final Block MILK_BLOCK = registerBlock(
            "milk_block",
            new Block(FabricBlockSettings.of(Material.SNOW_BLOCK).requiresTool().hardness(1.25f).resistance(150).sounds(BlockSoundGroup.WOOL)){{

            }},
            new Item.Settings()
            ),

    CANDYCANE_BLOCK_RED = registerBlock("candycane_block_red",
            new Block(FabricBlockSettings.of(Material.WOOL).breakByHand(true).hardness(1.25f).resistance(15).sounds(BlockSoundGroup.BONE)),
            new Item.Settings()
    ),

    CANDYCANE_BLOCK_GREEN = registerBlock("candycane_block_green",
            new Block(FabricBlockSettings.of(Material.WOOL).breakByHand(true).hardness(1.25f).resistance(15).sounds(BlockSoundGroup.BONE)),
            new Item.Settings()
    ),

    CANDYCANE_BLOCK_YELLOW = registerBlock("candycane_block_yellow",
            new Block(FabricBlockSettings.of(Material.WOOL).breakByHand(true).hardness(1.25f).resistance(15).sounds(BlockSoundGroup.BONE)),
            new Item.Settings()
    );

    public static void registerBlocks(){
        MOD_BLOCKS.forEach(b -> {
            Registry.register(Registry.BLOCK, new Identifier(Christmas.MOD_ID, b.registerName), b.blockEntry);

        });
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
