package net.collective.enchanced.client.render.enchantments.weaving.hud;

import net.collective.enchanced.Enchanced;
import net.collective.enchanced.client.render.hud.PlayerHudElement;
import net.collective.enchanced.common.cca.entity.WeavingComponent;
import net.collective.enchanced.common.index.ModEntityComponents;
import net.collectively.geode.math.math;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static net.collective.enchanced.Enchanced.id;

public class WeavingHudElement extends PlayerHudElement {
    public static final Identifier IDENTIFIER = id("weaving");

    private static final Identifier LEFT_FULL_TEXTURE = id("hud/double_jump_left_full");
    private static final Identifier LEFT_EMPTY_TEXTURE = id("hud/double_jump_left_empty");
    private static final Identifier RIGHT_FULL_TEXTURE = id("hud/double_jump_right_full");
    private static final Identifier RIGHT_EMPTY_TEXTURE = id("hud/double_jump_right_empty");

    @Override
    protected void renderWithPlayer(ClientPlayerEntity player, @NotNull DrawContext context, double tickDelta) {
        WeavingComponent component = player.getComponent(ModEntityComponents.WEAVING);

        if (!component.hasWeaving()) {
            return;
        }

        int centerX = context.getScaledWindowWidth() / 2;
        int centerY = context.getScaledWindowHeight() / 2;

        if (component.count() == WeavingComponent.MAX_JUMPS && component.regenerationProgress() >= 1f) {
            return;
        }

        float opacity = 1f;
        int y = centerY - 3;
        int count = WeavingComponent.MAX_JUMPS;
        float rows = count / 2f;
        y -= math.round((rows - 1) * (5));

        for (int i = 0; i < count; i++) {
            boolean isLeftHandSide = i % 2 == 0;
            boolean isFullyCharged = component.count() < i;
            boolean isCharging = component.count() == i;
            double progress = isFullyCharged ? 0 : isCharging ? component.regenerationProgress() : 1;

            Identifier emptyTexture = isLeftHandSide ? LEFT_EMPTY_TEXTURE : RIGHT_EMPTY_TEXTURE;
            Identifier chargedTexture = isLeftHandSide ? LEFT_FULL_TEXTURE : RIGHT_FULL_TEXTURE;

            int x = centerX + (isLeftHandSide ? -8 - 8 : 8 - 1);

            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, emptyTexture, x, y, 8, 8, opacity);
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, chargedTexture, 8, 8, 0, 0, x, y, (int) math.round(progress * 8), 8);

            if (!isLeftHandSide) {
                y += 8 + 2;
            }
        }
    }
}
