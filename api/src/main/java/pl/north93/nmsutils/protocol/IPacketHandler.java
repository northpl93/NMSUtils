package pl.north93.nmsutils.protocol;

import org.bukkit.entity.Player;

public interface IPacketHandler
{
    boolean onPacketSend(Player player, Object packet);

    boolean onPacketReceive(Player player, Object packet);
}
