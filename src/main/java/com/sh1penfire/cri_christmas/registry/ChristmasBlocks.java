package com.sh1penfire.cri_christmas.registry;

import com.sh1penfire.cri_christmas.Christmas;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ChristmasBlocks {

    //blocks
    public static final Block MILK_BLOCK = new Block(FabricBlockSettings.of(Material.WOOL).breakByHand(true).hardness(1.25f).resistance(150).sounds(BlockSoundGroup.WOOL)){{

    }},

    CANDYCANE_BLOCK_RED = new Block(FabricBlockSettings.of(Material.WOOL).breakByHand(true).hardness(1.25f).resistance(15).sounds(BlockSoundGroup.BONE)){{

    }},

    CANDYCANE_BLOCK_GREEN = new Block(FabricBlockSettings.of(Material.WOOL).breakByHand(true).hardness(1.25f).resistance(15).sounds(BlockSoundGroup.BONE)){{

    }},

    CANDYCANE_BLOCK_YELLOW = new Block(FabricBlockSettings.of(Material.WOOL).breakByHand(true).hardness(1.25f).resistance(15).sounds(BlockSoundGroup.BONE)){{

    }};

    public static void registerBlocks(){
        Registry.register(Registry.BLOCK, new Identifier(Christmas.MOD_ID, "milk_block"), MILK_BLOCK);

        Registry.register(Registry.BLOCK, new Identifier(Christmas.MOD_ID, "candycane_block_red"), CANDYCANE_BLOCK_RED);
        Registry.register(Registry.BLOCK, new Identifier(Christmas.MOD_ID, "candycane_block_green"), CANDYCANE_BLOCK_GREEN);
        Registry.register(Registry.BLOCK, new Identifier(Christmas.MOD_ID, "candycane_block_yellow"), CANDYCANE_BLOCK_YELLOW);
    }
}
