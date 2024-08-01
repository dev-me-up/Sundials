package com.github.devmeup.sundials.references;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reference {
    public static final String MOD_ID = "sundials";
    public static final Logger LOGGER = LoggerFactory.getLogger("sundials");

    @NotNull
    public static Identifier identifier(@NotNull String path) {
        return Identifier.of(MOD_ID, path);
    }

    public static MutableText translate(String key, Object ... params) {
        return Text.translatable(MOD_ID + "." + key, params);
    }

}
