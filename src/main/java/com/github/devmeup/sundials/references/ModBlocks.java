package com.github.devmeup.sundials.references;

import com.github.devmeup.sundials.block.ChronodialBlock;
import com.github.devmeup.sundials.block.SundialBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static com.github.devmeup.sundials.references.Reference.*;

public class ModBlocks {
    public static final Block SUNDIAL = register("sundial", SundialBlock::new, AbstractBlock.Settings
            .copy(Blocks.QUARTZ_BLOCK).nonOpaque());

    public static final Block CHRONODIAL = register("chronodial", ChronodialBlock::new, AbstractBlock.Settings
            .copy(Blocks.QUARTZ_BLOCK).nonOpaque());

    public static void initialize() {
        LOGGER.info(MOD_ID + " --- Registering blocks and block items for mod...");
    }

    public static Block register(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier id = Identifier.of(MOD_ID, name);
        final RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);

        final Block block = Blocks.register(key, factory, settings);
        Items.register(block);

        return block;
    }

}
