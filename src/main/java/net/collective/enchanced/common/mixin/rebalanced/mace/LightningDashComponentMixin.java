package net.collective.enchanced.common.mixin.rebalanced.mace;

import moriyashiine.enchancement.common.component.entity.LightningDashComponent;
import moriyashiine.enchancement.common.init.ModEntityComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningDashComponent.class)
public abstract class LightningDashComponentMixin {
    @Shadow public abstract boolean isSmashing();
    @Shadow @Final private PlayerEntity obj;

    @Inject(method = {"tick"}, at = @At("HEAD"))
    public void enchanced$rebalanced(CallbackInfo ci){
        if (this.isSmashing() && this.obj.getMainHandStack().isOf(Items.MACE)) {
            (ModEntityComponents.GROUNDED_COOLDOWN.get(this.obj)).putOnCooldown(this.obj.getMainHandStack(), 440);
        }
    }
}
