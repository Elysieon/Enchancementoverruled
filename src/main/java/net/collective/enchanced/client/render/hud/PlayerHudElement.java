package net.collective.enchanced.client.render.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import org.jetbrains.annotations.NotNull;

public abstract class PlayerHudElement implements HudElement {
    @Override
    public void render(@NotNull DrawContext context, @NotNull RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;

        if (player == null) {
            return;
        }

        renderWithPlayer(player, context, tickCounter.getDynamicDeltaTicks());
    }

    protected abstract void renderWithPlayer(ClientPlayerEntity player, @NotNull DrawContext context, double tickDelta);
}
