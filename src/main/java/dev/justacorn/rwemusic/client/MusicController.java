package dev.justacorn.rwemusic.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundSource;

/** Owns one soundtrack instance and restarts it if Minecraft reloads its sound engine. */
public final class MusicController {
    private static LoopingMusicSound current;
    private static boolean clearVanillaBeforeNextStart = true;

    private MusicController() { }

    public static void tick(Minecraft client) {
        SoundManager soundManager = client.getSoundManager();

        if (!MusicConfig.isEnabled()) {
            stop(client);
            return;
        }

        if (current != null) {
            if (soundManager.isActive(current)) {
                return;
            }
            // Sound-engine/resource reloads invalidate the old instance. Recreate it below.
            current = null;
        }

        if (clearVanillaBeforeNextStart) {
            soundManager.stop(null, SoundSource.MUSIC);
            clearVanillaBeforeNextStart = false;
        }

        LoopingMusicSound next = new LoopingMusicSound();
        SoundEngine.PlayResult result = soundManager.play(next);
        if (result != SoundEngine.PlayResult.NOT_STARTED) {
            current = next;
        }
        // If the sound engine is not ready yet, the next client tick retries without
        // creating/retaining a phantom active instance.
    }

    /** Called when the Mod Menu setting changes. */
    public static void refresh(Minecraft client) {
        stop(client);
        clearVanillaBeforeNextStart = MusicConfig.isEnabled();
    }

    private static void stop(Minecraft client) {
        if (current != null) {
            client.getSoundManager().stop(current);
            current = null;
        }
    }
}
