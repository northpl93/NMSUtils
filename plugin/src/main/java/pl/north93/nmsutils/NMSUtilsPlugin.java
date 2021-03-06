package pl.north93.nmsutils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import org.apache.commons.lang.StringUtils;

import pl.north93.nmsutils.protocol.IProtocolManager;
import pl.north93.nmsutils.protocol.ProtocolManagerFactory;
import pl.north93.nmsutils.tracker.EntityTrackerManager;
import pl.north93.nmsutils.tracker.EntityTrackerManagerFactory;

public class NMSUtilsPlugin extends JavaPlugin implements NMSUtils
{
    private static final String SERVER_VERSION;
    private IProtocolManager protocolManager;

    static
    {
        final String craftServerName = Bukkit.getServer().getClass().getName();
        SERVER_VERSION = StringUtils.split(craftServerName, '.')[3];
    }

    @Override
    public String getServerVersion()
    {
        return SERVER_VERSION;
    }

    @Override
    public IProtocolManager getProtocolManager()
    {
        return this.protocolManager;
    }

    @Override
    public void onEnable()
    {
        this.protocolManager = ProtocolManagerFactory.createProtocolManager(SERVER_VERSION);

        final EntityTrackerManager entityTrackerManager = EntityTrackerManagerFactory.createEntityTrackerManager(SERVER_VERSION);
        this.getServer().getPluginManager().registerEvents(entityTrackerManager, this);
    }
}
