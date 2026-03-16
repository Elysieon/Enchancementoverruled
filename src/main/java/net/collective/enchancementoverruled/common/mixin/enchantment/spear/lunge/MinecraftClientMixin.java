package net.collective.enchancementoverruled.common.mixin.enchantment.spear.lunge;

import net.collective.enchancementoverruled.common.index.OverruledEnchantments;
import net.collective.enchancementoverruled.common.payload.LungeC2SPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow
    @Final
    public GameOptions options;

    @Inject(method = {"handleInputEvents"}, at = {@At("HEAD")})
    private void lunge$handleInputEvents(CallbackInfo cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        if (player != null) {
            var enchantmentRegistry = player.getRegistryManager().getEntryOrThrow(Enchantments.LUNGE);
            int level = EnchantmentHelper.getLevel(enchantmentRegistry, Objects.requireNonNull(player.getActiveOrMainHandStack()));
            if (level > 0 && player.isUsingItem()) {
                while (this.options.attackKey.wasPressed()) {
                    LungeC2SPayload payload = new LungeC2SPayload(player.getId());
                    ClientPlayNetworking.send(payload);
                }
            }
        }
    }
}
