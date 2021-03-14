package pl.north93.nmsutils.protocol;

import org.bukkit.entity.Player;

interface IProtocolInjector
{
    void doInject(InjectorContext context) throws Exception;

    Player extractPlayerFromChannel(Object channel);
}
