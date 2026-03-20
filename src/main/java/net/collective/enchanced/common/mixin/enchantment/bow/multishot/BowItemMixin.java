package net.collective.enchanced.common.mixin.enchantment.bow.multishot;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.collective.enchanced.common.index.EnchancedEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Mixin(BowItem.class)
public class BowItemMixin {
    @ModifyExpressionValue(
            method = "onStoppedUsing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/BowItem;load(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;)Ljava/util/List;"
            )
    )
    private List<ItemStack> enchanced$onStoppedUsing(List<ItemStack> original, ItemStack stack, World world) {
        if (!EnchancedEnchantments.hasEnchantment(world.getRegistryManager(), stack, EnchancedEnchantments.MULTISHOT)) {
            return original;
        }

        original.add(original.getFirst());
        original.add(original.getFirst());
        return original;
    }
}
