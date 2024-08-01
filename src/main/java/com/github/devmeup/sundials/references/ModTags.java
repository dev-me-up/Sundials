package com.github.devmeup.sundials.references;

import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

import static com.github.devmeup.sundials.references.Reference.identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> SUNLIGHT_TRAVERSABLE_BLOCKS =
                createTag("sunlight_traversable_blocks");
        public static final TagKey<Block> GRATE_BLOCKS =
                createTag("grate_blocks");

        private static TagKey<net.minecraft.block.Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, identifier(name));
        }
    }

    public static class Fluids {
        public static final TagKey<Fluid> SUNLIGHT_TRAVERSABLE_FLUIDS =
                createTag("sunlight_traversable_fluids");

        private static TagKey<Fluid> createTag(String name) {
            return TagKey.of(RegistryKeys.FLUID, identifier(name));
        }
    }

}
