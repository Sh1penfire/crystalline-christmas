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

    }};

    public static void registerBlocks(){
        Registry.register(Registry.BLOCK, new Identifier(Christmas.MOD_ID, "milk_block"), MILK_BLOCK);
    }
}
