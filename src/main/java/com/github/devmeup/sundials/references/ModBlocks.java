package com.github.devmeup.sundials.references;

import com.github.devmeup.sundials.block.ChronodialBlock;
import com.github.devmeup.sundials.block.SundialBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.github.devmeup.sundials.references.Reference.*;

public class ModBlocks {
    public static final Block SUNDIAL = registerBlock("sundial",
            new SundialBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK)));
    public static final Block CHRONODIAL = registerBlock("chronodial",
            new ChronodialBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK)));

    public ModBlocks() {
        throw new AssertionError();
    }

    public static void register() {
        LOGGER.info(MOD_ID + " --- Registering blocks for mod...");
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, identifier(name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, identifier(name), new BlockItem(block, new Item.Settings()));
    }

}
