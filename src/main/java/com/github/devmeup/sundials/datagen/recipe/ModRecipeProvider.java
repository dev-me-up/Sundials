package com.github.devmeup.sundials.datagen.recipe;

import com.github.devmeup.sundials.references.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static com.github.devmeup.sundials.references.Reference.MOD_ID;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        LOGGER.info(MOD_ID + " --- Generating recipe data for mod...");

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.SUNDIAL, 1)
                .pattern("SIS")
                .pattern(" P ")
                .pattern("SSS")
                .input('I', Items.IRON_INGOT)
                .input('P', Blocks.QUARTZ_PILLAR)
                .input('S', Blocks.QUARTZ_SLAB)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Blocks.QUARTZ_PILLAR), conditionsFromItem(Blocks.QUARTZ_PILLAR))
                .criterion(hasItem(Blocks.QUARTZ_SLAB), conditionsFromItem(Blocks.QUARTZ_SLAB))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.SUNDIAL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.CHRONODIAL, 1)
                .pattern("G")
                .pattern("*")
                .pattern("S")
                .input('G', Items.GOLD_INGOT)
                .input('*', Items.NETHER_STAR)
                .input('S', ModBlocks.SUNDIAL)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                .criterion(hasItem(ModBlocks.SUNDIAL), conditionsFromItem(ModBlocks.SUNDIAL))
                .offerTo(exporter, Identifier.of(getRecipeName(ModBlocks.CHRONODIAL)));
    }

}
