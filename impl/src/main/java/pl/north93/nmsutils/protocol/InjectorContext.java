package pl.north93.nmsutils.protocol;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
class InjectorContext implements IPacketHandler
{
    private final Server server;
    private final IProtocolInjector injector;
    private final IPacketHandler packetHandler;

    public Server getServer()
    {
        return this.server;
    }

    public void callEvent(final Event event)
    {
        this.server.getPluginManager().callEvent(event);
    }

    public Player getPlayerFromChannel(final Object channel)
    {
        return this.injector.extractPlayerFromChannel(channel);
    }

    @Override
    public boolean onPacketSend(final Player player, final Object packet)
    {
        return this.packetHandler.onPacketSend(player, packet);
    }

    @Override
    public boolean onPacketReceive(final Player player, final Object packet)
    {
        return this.packetHandler.onPacketReceive(player, packet);
    }
}
