package pl.north93.nmsutils.tracker;

public final class EntityTrackerManagerFactory
{
    public static EntityTrackerManager createEntityTrackerManager(final String serverVersion)
    {
        return new EntityTrackerManager(createInjector(serverVersion));
    }

    private static IEntityTrackerInjector createInjector(final String serverVersion)
    {
        switch (serverVersion)
        {
            case "v1_12_R1":
                return new EntityTrackerInjector1_12R1();
            case "v1_16_R3":
                return new EntityTrackerInjector1_16R3();
            default:
                throw new IllegalArgumentException("Unsupported version " + serverVersion);
        }
    }
}
