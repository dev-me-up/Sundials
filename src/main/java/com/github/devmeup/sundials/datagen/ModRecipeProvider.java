package com.github.devmeup.sundials.datagen;

import com.github.devmeup.sundials.references.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static com.github.devmeup.sundials.references.Reference.MOD_ID;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                LOGGER.info(MOD_ID + " --- Generating recipe data for mod...");

                RegistryEntryLookup<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.DECORATIONS, ModBlocks.SUNDIAL, 1)
                        .pattern("SIS")
                        .pattern(" P ")
                        .pattern("SSS")
                        .input('I', Items.IRON_INGOT)
                        .input('P', Blocks.QUARTZ_PILLAR)
                        .input('S', Blocks.QUARTZ_SLAB)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(Blocks.QUARTZ_PILLAR), conditionsFromItem(Blocks.QUARTZ_PILLAR))
                        .criterion(hasItem(Blocks.QUARTZ_SLAB), conditionsFromItem(Blocks.QUARTZ_SLAB))
                        .offerTo(exporter, String.valueOf(Identifier.of(getRecipeName(ModBlocks.SUNDIAL))));

                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.DECORATIONS, ModBlocks.CHRONODIAL, 1)
                        .pattern("G")
                        .pattern("*")
                        .pattern("S")
                        .input('G', Items.GOLD_INGOT)
                        .input('*', Items.NETHER_STAR)
                        .input('S', ModBlocks.SUNDIAL)
                        .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                        .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                        .criterion(hasItem(ModBlocks.SUNDIAL), conditionsFromItem(ModBlocks.SUNDIAL))
                        .offerTo(exporter, String.valueOf(Identifier.of(getRecipeName(ModBlocks.CHRONODIAL))));
            }
        };
    }

    @Override
    public String getName() {
        return "";
    }

}
