package net.collective.enchanced.common.cca;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public abstract class SyncedLivingEntityComponent implements Component, AutoSyncedComponent, CommonTickingComponent {
    private final LivingEntity livingEntity;

    public SyncedLivingEntityComponent(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }

    // region ---- RECORD ACCESSOR ----

    public LivingEntity livingEntity() {
        return livingEntity;
    }

    public World world() {
        return livingEntity.getEntityWorld();
    }

    // endregion

    // region -------- SYNCING --------

    protected abstract ComponentKey<? extends SyncedLivingEntityComponent> getComponentKey();

    public final void sync(LivingEntity target) {
        getComponentKey().sync(target);
    }

    public final void sync() {
        sync(livingEntity);
    }

    // endregion
}