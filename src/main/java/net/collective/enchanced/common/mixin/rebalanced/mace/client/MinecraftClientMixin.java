package net.collective.enchanced.common.mixin.rebalanced.mace.client;


import net.collective.enchanced.common.payload.LungeC2SPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = {"doAttack"}, at = {@At("HEAD")}, cancellable = true)
    private void enchanced$rebalanced(CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        if (player != null && player.getMainHandStack().isOf(Items.MACE)) {
            if (player.getItemCooldownManager().isCoolingDown(player.getMainHandStack())) cir.setReturnValue(false);
        }
    }
}
