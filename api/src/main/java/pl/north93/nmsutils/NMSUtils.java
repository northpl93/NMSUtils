package pl.north93.nmsutils;

import pl.north93.nmsutils.protocol.IProtocolManager;

public interface NMSUtils
{
    String getServerVersion();

    IProtocolManager getProtocolManager();
}
