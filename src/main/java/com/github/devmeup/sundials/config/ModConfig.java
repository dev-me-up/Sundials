package com.github.devmeup.sundials.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfig {
    public static final ConfigClassHandler<ModConfig> CONFIG = ConfigClassHandler.createBuilder(ModConfig.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("sundials.json"))
                    .build())
            .build();

    @SerialEntry public boolean applyTemporalShockDamage;
    @SerialEntry public int temporalShockDamage;

    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(CONFIG, ((((defaults, config, builder) -> builder
                .title(Text.literal("Sundials Configuration"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Sundials Mod Settings"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Chronodial Settings"))
                                .description(OptionDescription.of(Text.literal("Settings for the Chronodial block.")))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Damage On Use"))
                                        .description(OptionDescription.of(Text.literal("Sets whether or not the Chronodial damages the player.")))
                                        .binding(true,
                                                () -> config.applyTemporalShockDamage,
                                                newVal -> config.applyTemporalShockDamage = newVal)
                                        .controller(opt -> BooleanControllerBuilder.create(opt)
                                                .coloured(true))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Text.literal("Damage Dealt"))
                                        .description(OptionDescription.of(Text.literal(
                                                "Sets how much damage the Chronodial inflicts on use." +
                                                "\n1 damage = 1/2 heart of damage.")))
                                        .binding(10,
                                                () ->config.temporalShockDamage,
                                                newVal -> config.temporalShockDamage = newVal)
                                        .controller(opt -> IntegerFieldControllerBuilder.create(opt)
                                                .min(1))
                                        .build())
                                .build())
                        .build())
        )))).generateScreen(parent);
    }
}
