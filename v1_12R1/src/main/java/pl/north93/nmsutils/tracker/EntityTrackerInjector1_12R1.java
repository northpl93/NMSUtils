package pl.north93.nmsutils.tracker;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.EntityTrackerEntry;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class EntityTrackerInjector1_12R1 implements IEntityTrackerInjector
{
    private static final Field TRACKER_FIELD;

    static
    {
        try
        {
            TRACKER_FIELD = Entity.class.getDeclaredField("tracker");
            TRACKER_FIELD.setAccessible(true);
        }
        catch (final NoSuchFieldException e)
        {
            throw new RuntimeException("Failed to initialise EntityTrackerInjector for 1.12R1", e);
        }
    }

    @Override
    public void injectEntity(final org.bukkit.entity.Entity entity)
    {
        final CraftEntity craftEntity = (CraftEntity) entity;

        try
        {
            final EntityTrackerEntry entityTrackerEntry = (EntityTrackerEntry) TRACKER_FIELD.get(craftEntity.getHandle());
            final Map<EntityPlayer, Boolean> trackedPlayerMap = entityTrackerEntry.trackedPlayerMap;

            entityTrackerEntry.trackedPlayerMap = new EntityTrackerMap<>(new EntityTrackerCallback1_12R1(entity), trackedPlayerMap);
            entityTrackerEntry.trackedPlayers = entityTrackerEntry.trackedPlayerMap.keySet();
        }
        catch (final Exception e)
        {
            log.error("Failed to inject entity", e);
        }
    }
}
