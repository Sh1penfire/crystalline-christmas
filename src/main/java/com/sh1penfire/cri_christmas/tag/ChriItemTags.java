package com.sh1penfire.cri_christmas.tag;

import net.minecraft.item.Item;
import net.minecraft.tag.RequiredTagList;
import net.minecraft.tag.RequiredTagListRegistry;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class ChriItemTags {
    public static RequiredTagList<Item> REQUIRED_TAGS;
    public static Tag.Identified<Item> SNOWBALL;

    private static Tag.Identified<Item> register(String id) {
        return REQUIRED_TAGS.add(id);
    }

    public void load(){
        REQUIRED_TAGS = RequiredTagListRegistry.register(Registry.ITEM_KEY, "tags/items");
        SNOWBALL = register("snowball");
    }
}
