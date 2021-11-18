package com.sh1penfire.cri_christmas;

import com.sh1penfire.cri_christmas.registry.ChristmasItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.tinyremapper.extension.mixin.common.Logger;

public class Christmas implements ModInitializer {

    public static final Logger LOGGER = new Logger(Logger.Level.INFO);
    public static final String MOD_ID = "crymas";

    @Override
    public void onInitialize() {
        ChristmasItems.load();
    }
}