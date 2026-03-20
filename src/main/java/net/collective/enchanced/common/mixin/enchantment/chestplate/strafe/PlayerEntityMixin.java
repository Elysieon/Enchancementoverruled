package net.collective.enchanced.common.mixin.enchantment.chestplate.strafe;


import moriyashiine.enchancement.common.init.ModEnchantmentEffectComponentTypes;
import moriyashiine.enchancement.common.util.EnchancementUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class PlayerEntityMixin {

    @Inject(method = {"travel"}, at = {@At("HEAD")})
    private void enchancement$AirVelocityStrafe(Vec3d movementInput, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        ItemStack chestStack = entity.getEquippedStack(EquipmentSlot.CHEST);
        if (!chestStack.isEmpty()) {
            boolean hasSticky = (EnchancementUtil.hasAnyEnchantmentsWith(entity, ModEnchantmentEffectComponentTypes.DIRECTION_BURST));
            if (hasSticky) {
                Vec3d velocity = entity.getVelocity();
                double boost = 1.03;
                double newX = velocity.x * boost;
                double newZ = velocity.z * boost;
                entity.setVelocity(newX, velocity.y, newZ);
            }
        }
    }
}