package dev.justacorn.rwemusic;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public final class RealisticWaterEraMusic implements ModInitializer {
    public static final String MOD_ID = "realistic_water_era_music";
    public static final Identifier ERA_THEME_ID = Identifier.fromNamespaceAndPath(MOD_ID, "era_theme");
    public static final SoundEvent ERA_THEME = Registry.register(
            BuiltInRegistries.SOUND_EVENT,
            ERA_THEME_ID,
            SoundEvent.createVariableRangeEvent(ERA_THEME_ID)
    );

    @Override
    public void onInitialize() {
        // Touch the field so registration is guaranteed during mod initialization.
        if (ERA_THEME == null) {
            throw new IllegalStateException("Could not register the era theme sound event");
        }
    }
}
