package pl.north93.nmsutils.tracker;

import java.util.Map;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.PlayerChunkMap.EntityTracker;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;

class EntityTrackerInjector1_16R3 implements IEntityTrackerInjector
{
    @Override
    public void injectEntity(final org.bukkit.entity.Entity entity)
    {
        final CraftEntity craftEntity = (CraftEntity) entity;
        final EntityTracker entityTracker = craftEntity.getHandle().tracker;

        if (entityTracker == null)
        {
            // todo this happens when Entity is Player, debug why and try to fix
            return;
        }

        final Map<EntityPlayer, Boolean> trackedPlayerMap = entityTracker.trackedPlayerMap;
        entityTracker.trackedPlayerMap = new EntityTrackerMap<>(new EntityTrackerCallback1_16R3(entity), trackedPlayerMap);

        entityTracker.trackedPlayers = entityTracker.trackedPlayerMap.keySet();
    }
}
