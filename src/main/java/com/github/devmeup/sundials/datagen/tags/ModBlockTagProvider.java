package com.github.devmeup.sundials.datagen.tags;

import com.github.devmeup.sundials.references.ModBlocks;
import com.github.devmeup.sundials.references.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

import static com.github.devmeup.sundials.references.Reference.MOD_ID;
import static net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags.*;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        LOGGER.info(MOD_ID + " --- Generating block tag data for mod...");

        getOrCreateTagBuilder(ModTags.Blocks.SUNLIGHT_TRAVERSABLE_BLOCKS)
                .forceAddTag(ModTags.Blocks.GRATE_BLOCKS) // All grates should allow sunlight to pass
                .forceAddTag(BlockTags.TRAPDOORS)
                .forceAddTag(BlockTags.CLIMBABLE)
                .forceAddTag(BlockTags.LEAVES)
                .forceAddTag(BlockTags.BANNERS)
                .forceAddTag(GLASS_BLOCKS)
                .forceAddTag(GLASS_PANES)
                .add(Blocks.ICE);
        getOrCreateTagBuilder(ModTags.Blocks.GRATE_BLOCKS)
                .add(Blocks.COPPER_GRATE)
                .add(Blocks.WAXED_COPPER_GRATE)
                .add(Blocks.EXPOSED_COPPER_GRATE)
                .add(Blocks.WAXED_EXPOSED_COPPER_GRATE)
                .add(Blocks.WEATHERED_COPPER_GRATE)
                .add(Blocks.WAXED_WEATHERED_COPPER_GRATE)
                .add(Blocks.OXIDIZED_COPPER_GRATE)
                .add(Blocks.WAXED_OXIDIZED_COPPER_GRATE);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.SUNDIAL)
                .add(ModBlocks.CHRONODIAL);
    }

}
