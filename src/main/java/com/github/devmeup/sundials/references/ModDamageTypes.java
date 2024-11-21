package com.github.devmeup.sundials.references;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import static com.github.devmeup.sundials.references.Reference.LOGGER;
import static com.github.devmeup.sundials.references.Reference.MOD_ID;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> TEMPORAL_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID, "temporal_damage"));

    public static void initialize() {
        LOGGER.info(MOD_ID + " --- Registering damage types for mod...");
    }

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().getOptionalEntry(key).orElse(null));
    }

}
