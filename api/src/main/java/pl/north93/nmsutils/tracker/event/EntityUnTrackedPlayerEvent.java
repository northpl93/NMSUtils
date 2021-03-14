package pl.north93.nmsutils.tracker.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import lombok.ToString;

@ToString
public class EntityUnTrackedPlayerEvent extends PlayerEvent
{
    private static final HandlerList handlers = new HandlerList();
    private final Entity entity;

    public EntityUnTrackedPlayerEvent(final Player who, final Entity entity)
    {
        super(who);
        this.entity = entity;
    }

    public Entity getEntity()
    {
        return this.entity;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}