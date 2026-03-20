package net.collective.enchanced.common.mixin.enchantment.crossbow.ballistic;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.collective.enchanced.common.cca.entity.BallisticComponent;
import net.collective.enchanced.common.index.EnchancedEnchantments;
import net.collective.enchanced.common.util.EnchantUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Unique
    private ItemStack ballistic$getRocketsStack() {
        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        for (int i = 0; i < playerEntity.getInventory().size(); i++) {
            ItemStack itemStack = playerEntity.getInventory().getStack(i);
            if (itemStack.isOf(Items.FIREWORK_ROCKET)) {
                return itemStack;
            }
        }

        return ItemStack.EMPTY;
    }

    @WrapMethod(method = "getProjectileType")
    private ItemStack ballistic$getProjectileType(ItemStack itemStack, Operation<ItemStack> original) {
        PlayerEntity playerEntity = (PlayerEntity) (Object) this;

        if (EnchantUtils.hasEnchantment(playerEntity, itemStack, EnchancedEnchantments.BALLISTIC)) {
            if (!itemStack.isOf(Items.FIREWORK_ROCKET)) {
                itemStack = ballistic$getRocketsStack();

                if (itemStack.isEmpty()) {
                    if (playerEntity.isCreative()) {
                        return BallisticComponent.getRandomFirework(playerEntity.getRandom());
                    }

                    return ItemStack.EMPTY;
                }

                return itemStack;
            }
        }

        return original.call(itemStack);
    }
}
