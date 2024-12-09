package com.github.devmeup.sundials.references;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.github.devmeup.sundials.references.Reference.LOGGER;
import static com.github.devmeup.sundials.references.Reference.MOD_ID;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> SUNDIAL_BLOCKS_GROUP_KEY = RegistryKey.of(
            Registries.ITEM_GROUP.getKey(),
            Identifier.of(MOD_ID, "sundials_blocks"));

    public static final ItemGroup SUNDIALS_BLOCKS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.SUNDIAL))
            .displayName(Text.translatable("itemGroup.sundials_blocks_group"))
            .build();

    public static void initialize() {
        LOGGER.info(MOD_ID + " --- Registering item groups for mod...");

        Registry.register(Registries.ITEM_GROUP, SUNDIAL_BLOCKS_GROUP_KEY, SUNDIALS_BLOCKS_GROUP);

        ItemGroupEvents.modifyEntriesEvent(SUNDIAL_BLOCKS_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModBlocks.SUNDIAL.asItem());
            itemGroup.add(ModBlocks.CHRONODIAL.asItem());
        });
    }

}
