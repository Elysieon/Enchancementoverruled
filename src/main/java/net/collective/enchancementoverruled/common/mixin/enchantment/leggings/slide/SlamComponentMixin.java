package net.collective.enchancementoverruled.common.mixin.enchantment.leggings.slide;


import moriyashiine.enchancement.api.event.MultiplyMovementSpeedEvent;
import moriyashiine.enchancement.common.component.entity.SlamComponent;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SlamComponent.class)
public class SlamComponentMixin {

    @Shadow @Final private PlayerEntity obj;
    @Shadow(remap = false) private int ticksLeftToJump;
    @Shadow(remap = false) private float strength;

    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public float getJumpBoostStrength() {
        return ticksLeftToJump > 0 ? (float) (MultiplyMovementSpeedEvent.getJumpStrength(obj, (1.0F + strength)) * 3) : 1.0F;
    }


}


