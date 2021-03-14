package pl.north93.nmsutils.protocol;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ProtocolManagerImpl implements IProtocolManager, IPacketHandler
{
    private final List<IPacketHandler> packetHandlers;

    public ProtocolManagerImpl()
    {
        this.packetHandlers = new ArrayList<>();

        final IProtocolInjector injector = new ProtocolInjector1_12_R1();
        try
        {
            injector.doInject(new InjectorContext(Bukkit.getServer(), injector, this));
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void registerHandler(final IPacketHandler packetHandler)
    {
        this.packetHandlers.add(packetHandler);
    }

    @Override
    public boolean onPacketSend(final Player player, final Object packet)
    {
        for (final IPacketHandler packetHandler : this.packetHandlers)
        {
            if (packetHandler.onPacketSend(player, packet))
            {
                continue;
            }

            return false;
        }

        return true;
    }

    @Override
    public boolean onPacketReceive(final Player player, final Object packet)
    {
        for (final IPacketHandler packetHandler : this.packetHandlers)
        {
            if (packetHandler.onPacketReceive(player, packet))
            {
                continue;
            }

            return false;
        }

        return true;
    }
}
