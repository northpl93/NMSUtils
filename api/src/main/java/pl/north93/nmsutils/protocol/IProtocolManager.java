package pl.north93.nmsutils.protocol;

public interface IProtocolManager
{
    void registerHandler(IPacketHandler packetHandler);
}
