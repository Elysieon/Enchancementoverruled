package net.collective.enchanced.common.mixin.rebalanced.disallowed_enchantments;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import moriyashiine.enchancement.common.util.EnchancementUtil;
import net.collective.enchanced.Enchanced;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;

@Mixin(EnchancementUtil.class)
public class EnchantmentUtilMixin {
    @ModifyReturnValue(
            method = "isEnchantmentAllowed(Lnet/minecraft/util/Identifier;)Z",
            at = @At("RETURN")
    )
    private static boolean enchanced$isEnchantmentAllowed(boolean original, Identifier enchantment) {
        return original && Arrays.stream(Enchanced.DISABLED_ENCHANTMENTS).noneMatch(enchantment::equals);
    }
}
