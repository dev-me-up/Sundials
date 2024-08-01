package com.github.devmeup.sundials;

import com.github.devmeup.sundials.datagen.loot_table.ModLootTableProvider;
import com.github.devmeup.sundials.datagen.recipe.ModRecipeProvider;
import com.github.devmeup.sundials.datagen.tags.ModBlockTagProvider;
import com.github.devmeup.sundials.datagen.tags.ModFluidTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

import static com.github.devmeup.sundials.references.Reference.LOGGER;
import static com.github.devmeup.sundials.references.Reference.MOD_ID;

public class SundialsDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        LOGGER.info(MOD_ID + " --- Generating data for mod...");

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModFluidTagProvider::new);
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModRecipeProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        LOGGER.info(MOD_ID + " --- Register data generation features for mod...");
    }
}
