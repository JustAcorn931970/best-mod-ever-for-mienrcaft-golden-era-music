package dev.justacorn.rwemusic.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class MusicConfigScreen extends Screen {
    private final Screen parent;
    private Button toggleButton;

    public MusicConfigScreen(Screen parent) {
        super(Component.literal("Realistic Water Era Music"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int center = this.width / 2;
        this.toggleButton = this.addRenderableWidget(Button.builder(toggleLabel(), button -> {
            MusicConfig.setEnabled(!MusicConfig.isEnabled());
            MusicController.refresh(Minecraft.getInstance());
            button.setMessage(toggleLabel());
        }).bounds(center - 100, this.height / 2 - 8, 200, 20).build());

        this.addRenderableWidget(Button.builder(Component.literal("Done"), button -> onClose())
                .bounds(center - 100, this.height / 2 + 24, 200, 20)
                .build());
    }

    private static Component toggleLabel() {
        return Component.literal("Music: " + (MusicConfig.isEnabled() ? "ON" : "OFF"));
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
        graphics.centeredText(this.font, this.title, this.width / 2, this.height / 2 - 58, 0xFFFFFFFF);
        graphics.centeredText(this.font,
                Component.literal("Loops Gaiety in the Golden Age from game startup until you close Minecraft."),
                this.width / 2, this.height / 2 - 34, 0xFFCCCCCC);
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().gui.setScreen(this.parent);
    }
}
