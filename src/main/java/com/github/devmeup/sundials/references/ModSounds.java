package com.github.devmeup.sundials.references;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

import static com.github.devmeup.sundials.references.Reference.LOGGER;
import static com.github.devmeup.sundials.references.Reference.identifier;

public class ModSounds {
    public static final SoundEvent TICK_TOCK = registerSoundEvent("tick_tock");

    public static void register() {
        LOGGER.info(Reference.MOD_ID + " --- Registering sounds for mod...");
    }

    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(Registries.SOUND_EVENT, identifier(name), SoundEvent.of(identifier(name)));
    }

}
