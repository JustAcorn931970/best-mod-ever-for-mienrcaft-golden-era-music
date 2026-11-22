package dev.justacorn.rwemusic.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public final class RweMusicClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MusicConfig.load();
        ClientTickEvents.END_CLIENT_TICK.register(MusicController::tick);
    }
}
