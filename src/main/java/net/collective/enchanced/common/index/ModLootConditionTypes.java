package net.collective.enchanced.common.index;

import com.mojang.serialization.MapCodec;
import net.collective.enchanced.Enchanced;
import net.collective.enchanced.common.loot.condition.UsingItemLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface ModLootConditionTypes {
    LootConditionType USING_ITEM = registerLootConditionType("using_item", UsingItemLootCondition.CODEC);

    static void init() {}

    static LootConditionType registerLootConditionType(String name, MapCodec<? extends LootCondition> codec) {
        return Registry.register(Registries.LOOT_CONDITION_TYPE, Enchanced.id(name), new LootConditionType(codec));
    }
}
