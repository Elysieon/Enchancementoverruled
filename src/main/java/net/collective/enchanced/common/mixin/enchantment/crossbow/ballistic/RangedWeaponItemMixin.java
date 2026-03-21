package net.collective.enchanced.common.mixin.enchantment.crossbow.ballistic;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.collective.enchanced.common.cca.entity.BallisticComponent;
import net.collective.enchanced.common.index.EnchancedEnchantments;
import net.collective.enchanced.common.index.ModEntityComponents;
import net.collective.enchanced.common.util.EnchantUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(RangedWeaponItem.class)
public abstract class RangedWeaponItemMixin {
    @ModifyVariable(
            method = "getProjectile",
            at = @At("RETURN"),
            ordinal = 1,
            argsOnly = true
    )
    private static ItemStack ballistic$load(ItemStack originalProjectileStack, ItemStack stack, ItemStack projectileStack, LivingEntity shooter) {
        if (shooter != null && !projectileStack.isOf(Items.FIREWORK_ROCKET) && (!(shooter instanceof PlayerEntity player) || player.isCreative())) {
            return BallisticComponent.getRandomFirework(shooter.getRandom());
        }

        return originalProjectileStack;
    }

    @ModifyExpressionValue(
            method = "shootAll",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/ProjectileEntity;spawn(Lnet/minecraft/entity/projectile/ProjectileEntity;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/item/ItemStack;Ljava/util/function/Consumer;)Lnet/minecraft/entity/projectile/ProjectileEntity;"
            )
    )
    private <T extends ProjectileEntity> T ballistic$shootAll(T original, ServerWorld serverWorld, LivingEntity shooter, Hand hand, ItemStack itemStack) {
        if (EnchantUtils.hasEnchantment(serverWorld, itemStack, EnchancedEnchantments.BALLISTIC) && shooter instanceof PlayerEntity player) {
            if (original instanceof FireworkRocketEntity fireworkRocketEntity) {
                player.getComponent(ModEntityComponents.BALLISTIC).addProjectile(fireworkRocketEntity);
            }
        }

        return original;
    }
}
