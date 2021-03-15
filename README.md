NMSUtils
========
Various useful utilities in the development of advanced plugins.

Features
--------
* Simple packet listener
* ChannelInitializeEvent - called when Minecraft is initialising channel's pipeline, so you can add your own stuff
* EntityTrackedPlayerEvent & EntityUnTrackedPlayerEvent - called when entity enters player's visible range

Supported versions
------------------
* 1.12.2
* 1.16.5

_Only Paper is supported_

How to use
----------

Example plugin listening for packets:
```java
public class ExamplePlugin extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        final NMSUtils nmsUtils = (NMSUtils) Bukkit.getPluginManager().getPlugin("NMSUtils");
        nmsUtils.getProtocolManager().registerHandler(new IPacketHandler()
        {
            @Override
            public boolean onPacketSend(final Player player, final Object packet)
            {
                return true; // if false then packet will be dropped
            }

            @Override
            public boolean onPacketReceive(final Player player, final Object packet)
            {
                return true;
            }
        });
    }
}
```

ChannelInitializeEvent & entity tracker events are regular Bukkit events, register your listeners in Bukkit's PluginManager.

How to build
------------
`./gradlew shade`  
You'll find plugin's jar in `plugin/build/libs`

Use `./gradlew publishApiPublicationToMavenLocal` to publish API in your local maven repo.