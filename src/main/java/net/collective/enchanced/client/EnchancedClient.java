package net.collective.enchanced.client;

import net.collective.enchanced.client.render.enchantments.weaving.hud.WeavingHudElement;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;

public class EnchancedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudElementRegistry.addFirst(WeavingHudElement.IDENTIFIER, new WeavingHudElement());
    }
}
