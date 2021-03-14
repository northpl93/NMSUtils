package pl.north93.nmsutils.tracker;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntityTrackerManager implements Listener
{
    private final IEntityTrackerInjector entityTrackerInjector;

    public EntityTrackerManager(final IEntityTrackerInjector entityTrackerInjector)
    {
        this.entityTrackerInjector = entityTrackerInjector;
    }

    @EventHandler
    public void injectMapIntoEntityTracker(final EntityAddToWorldEvent event)
    {
        this.entityTrackerInjector.injectEntity(event.getEntity());
    }
}
