package net.collective.enchanced.common.mixin.enchantment.boots.weaving;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import moriyashiine.enchancement.common.init.ModEnchantments;
import net.collective.enchanced.common.index.EnchancedEnchantments;
import net.minecraft.block.BlockState;
import net.minecraft.block.CobwebBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CobwebBlock.class)
public class CobwebBlockMixin {
    @Inject(at = @At("HEAD"), method = "onEntityCollision", cancellable = true)
    private void enchanced$onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean bl, CallbackInfo ci) {
        if (entity instanceof LivingEntity livingEntity) {
            ItemStack feetItemStack = livingEntity.getEquippedStack(EquipmentSlot.FEET);

            if (feetItemStack.isEmpty() || !EnchancedEnchantments.hasEnchantment(world.getRegistryManager(), feetItemStack, EnchancedEnchantments.WEAVING)) {
                return;
            }

            entity.slowMovement(state, new Vec3d(0.95, 1, 0.95));
            ci.cancel();
        }
    }
}
