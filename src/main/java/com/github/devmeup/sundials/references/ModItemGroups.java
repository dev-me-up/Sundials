package com.github.devmeup.sundials.references;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.github.devmeup.sundials.references.Reference.LOGGER;
import static com.github.devmeup.sundials.references.Reference.MOD_ID;

public class ModItemGroups {
    public static final ItemGroup SUNDIALS_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Reference.MOD_ID, "sundials_blocks"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.sundials_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.SUNDIAL)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.SUNDIAL);
                        entries.add(ModBlocks.CHRONODIAL);
                    }).build());

    public ModItemGroups() {
        throw new AssertionError();
    }

    public static void register() {
        LOGGER.info(MOD_ID + " --- Registering item groups for mod...");
    }

}
