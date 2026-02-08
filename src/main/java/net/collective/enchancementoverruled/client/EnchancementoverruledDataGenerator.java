package net.collective.enchancementoverruled.client;

import moriyashiine.enchancement.common.init.ModSoundEvents;
import moriyashiine.enchancement.common.loot.condition.AttackerBehindLootCondition;
import moriyashiine.enchancement.common.tag.ModDamageTypeTags;
import net.collective.enchancementoverruled.Enchancementoverruled;
import net.collective.enchancementoverruled.common.index.ModSoundEvent;
import net.collective.enchancementoverruled.common.index.OverruledEnchantments;
import net.collectively.geode.datagen.GeodeDataGeneration;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.AllOfEnchantmentEffects;
import net.minecraft.enchantment.effect.AttributeEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.entity.PlaySoundEnchantmentEffect;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.loot.condition.AllOfLootCondition;
import net.minecraft.loot.condition.DamageSourcePropertiesLootCondition;
import net.minecraft.predicate.TagPredicate;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EnchancementoverruledDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(Generator::new);
    }

    public static class Generator extends GeodeDataGeneration {

        public Generator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(dataOutput, registriesFuture);
        }

        @Override
        protected void generate() {
            addEnchantment(OverruledEnchantments.WEAVING)
                    .translateDescription("UwU")
                    .autoTranslate()
                    .enchantment(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                    .primaryItems(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                    .addSlot(AttributeModifierSlot.FEET)
                    .addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES, new AttributeEnchantmentEffect(linkedModId("enchantment.weaving"), EntityAttributes.SNEAKING_SPEED, EnchantmentLevelBasedValue.linear(0.65F), EntityAttributeModifier.Operation.ADD_VALUE))
                    .build();
            addEnchantment(OverruledEnchantments.STENOSIS)
                    .translateDescription("Increased damage when striking an enemy from behind.")
                    .autoTranslate()
                    .enchantment(ItemTags.AXES)
                    .primaryItems(ItemTags.AXES)
                    .build();
            addEnchantment(OverruledEnchantments.ABIDING)
                    .translateDescription("Repeated strikes increases your attack speed.")
                    .autoTranslate()
                    .enchantment(ItemTags.AXES)
                    .primaryItems(ItemTags.AXES)
                    .build();
        }

        @Override
        public String getModId() {
            return Enchancementoverruled.MOD_ID;
        }
    }
}
