package pl.north93.nmsutils.protocol.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import io.netty.channel.Channel;
import lombok.ToString;

@ToString(of = "channel")
public class ChannelInitializeEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    private final Channel channel;

    public ChannelInitializeEvent(final Channel channel)
    {
        super(true);
        this.channel = channel;
    }

    public Channel getChannel()
    {
        return this.channel;
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
