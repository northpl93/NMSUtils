package pl.north93.nmsutils.protocol;

public class ProtocolManagerFactory
{
    public static IProtocolManager createProtocolManager(final String serverVersion)
    {
        return new ProtocolManagerImpl(createInjector(serverVersion));
    }

    private static IProtocolInjector createInjector(final String serverVersion)
    {
        switch (serverVersion)
        {
            case "v1_12_R1":
                return new ProtocolInjector1_12R1();
            case "v1_16_R3":
                return new ProtocolInjector1_16R3();
            default:
                throw new IllegalArgumentException("Unsupported version " + serverVersion);
        }
    }
}
