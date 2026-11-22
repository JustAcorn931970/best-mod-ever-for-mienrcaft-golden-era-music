package dev.justacorn.rwemusic.mixin;

import dev.justacorn.rwemusic.client.MusicConfig;
import net.minecraft.client.sounds.MusicManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Stops vanilla's delayed/random background-music scheduler while our replacement is enabled. */
@Mixin(MusicManager.class)
public abstract class MusicManagerMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void realisticWaterEraMusic$cancelVanillaMusic(CallbackInfo callbackInfo) {
        if (MusicConfig.isEnabled()) {
            callbackInfo.cancel();
        }
    }
}
