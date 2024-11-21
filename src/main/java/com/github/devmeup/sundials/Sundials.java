package com.github.devmeup.sundials;

import com.github.devmeup.sundials.config.ModConfig;
import com.github.devmeup.sundials.references.*;
import net.fabricmc.api.ModInitializer;

import static com.github.devmeup.sundials.references.Reference.LOGGER;

public class Sundials implements ModInitializer {
    @Override
    public void onInitialize() {
        LOGGER.info(Reference.MOD_ID + " --- Initializing mod...");

        ModBlocks.initialize();
        ModDamageTypes.initialize();
        ModItemGroups.initialize();
        ModSounds.initialize();

        ModConfig.CONFIG.load();
    }

}
