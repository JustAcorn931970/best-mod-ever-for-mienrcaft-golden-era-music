package dev.justacorn.rwemusic.client;

import dev.justacorn.rwemusic.RealisticWaterEraMusic;
import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

/** A non-positional, streamed music instance that loops for the full game session. */
public final class LoopingMusicSound extends AbstractSoundInstance {
    public LoopingMusicSound() {
        super(RealisticWaterEraMusic.ERA_THEME, SoundSource.MUSIC, RandomSource.create());
        this.looping = true;
        this.delay = 0;
        this.relative = true;
        this.attenuation = SoundInstance.Attenuation.NONE;
        this.volume = 1.0F;
        this.pitch = 1.0F;
    }
}
