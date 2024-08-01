package com.github.devmeup.sundials.datagen.loot_table;

import com.github.devmeup.sundials.references.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

// TODO: add block drop loot table
import java.util.concurrent.CompletableFuture;

import static com.github.devmeup.sundials.references.Reference.MOD_ID;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        LOGGER.info(MOD_ID + " --- Generating loot table data for mod...");

        addDrop(ModBlocks.SUNDIAL);
        addDrop(ModBlocks.CHRONODIAL);
    }
}
