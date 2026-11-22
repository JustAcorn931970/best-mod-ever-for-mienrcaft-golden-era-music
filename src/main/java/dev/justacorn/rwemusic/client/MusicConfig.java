package dev.justacorn.rwemusic.client;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/** Tiny properties-based config: one setting, no extra config library. */
public final class MusicConfig {
    private static final Path FILE = FabricLoader.getInstance().getConfigDir()
            .resolve("realistic-water-era-music.properties");

    private static boolean enabled = true;

    private MusicConfig() { }

    public static void load() {
        if (!Files.exists(FILE)) {
            save();
            return;
        }

        Properties properties = new Properties();
        try (Reader reader = Files.newBufferedReader(FILE)) {
            properties.load(reader);
            enabled = Boolean.parseBoolean(properties.getProperty("enabled", "true"));
        } catch (IOException exception) {
            System.err.println("[Realistic Water Era Music] Could not read config: " + exception.getMessage());
            enabled = true;
        }
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean value) {
        enabled = value;
        save();
    }

    private static void save() {
        try {
            Files.createDirectories(FILE.getParent());
            Properties properties = new Properties();
            properties.setProperty("enabled", Boolean.toString(enabled));
            try (Writer writer = Files.newBufferedWriter(FILE)) {
                properties.store(writer, "Realistic Water Era Music settings");
            }
        } catch (IOException exception) {
            System.err.println("[Realistic Water Era Music] Could not save config: " + exception.getMessage());
        }
    }
}
