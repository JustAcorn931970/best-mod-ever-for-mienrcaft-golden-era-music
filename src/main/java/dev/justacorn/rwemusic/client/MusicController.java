package dev.justacorn.rwemusic.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundSource;

/** Owns exactly one music instance and restores it if Minecraft reloads its sound engine. */
public final class MusicController {
    private static LoopingMusicSound current;

    private MusicController() { }

    public static void tick(Minecraft client) {
        SoundManager soundManager = client.getSoundManager();

        if (!MusicConfig.isEnabled()) {
            stop(client);
            return;
        }

        if (current != null && soundManager.isActive(current)) {
            return;
        }

        // On first start/re-enable, clear any vanilla background track before starting ours.
        if (current == null) {
            soundManager.stop(null, SoundSource.MUSIC);
        }

        current = new LoopingMusicSound();
        soundManager.play(current);
    }

    public static void refresh(Minecraft client) {
        stop(client);
        // When enabled, the end-of-tick callback starts the track immediately on the next tick.
    }

    private static void stop(Minecraft client) {
        if (current != null) {
            client.getSoundManager().stop(current);
            current = null;
        }
    }
}
