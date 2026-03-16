package net.collective.enchanced.common.index;

import net.collective.enchanced.Enchanced;
import net.collective.enchanced.common.cca.HasteComponent;
import net.minecraft.entity.LivingEntity;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class ModEntityComponents implements EntityComponentInitializer {
    public static final ComponentKey<HasteComponent> HASTE = ComponentRegistry.getOrCreate(Enchanced.id("haste"), HasteComponent.class);

    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(LivingEntity.class, HASTE).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(HasteComponent::new);
    }
}