package pl.north93.nmsutils.protocol;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.server.v1_12_R1.NetworkManager;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import net.minecraft.server.v1_12_R1.ServerConnection;

import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.Player;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;

class ProtocolInjector1_12_R1 implements IProtocolInjector
{
    @Override
    public void doInject(final InjectorContext context) throws Exception
    {
        final CraftServer craftServer = (CraftServer) context.getServer();
        final ServerConnection serverConnection = craftServer.getServer().getServerConnection();

        final Class<?> serverConnectionClass = serverConnection.getClass();

        final Field activeListenersField = serverConnectionClass.getDeclaredField("g");
        activeListenersField.setAccessible(true);

        final List<ChannelFuture> activeListeners = (List<ChannelFuture>) activeListenersField.get(serverConnection);
        for (final ChannelFuture activeListener : activeListeners)
        {
            // In normal Minecraft server there is only one listening socket, but there is a way
            // to register more sockets through a method in NMS, so handle that case
            this.injectIntoServerSocket(context, activeListener);
        }
    }

    private void injectIntoServerSocket(final InjectorContext context, final ChannelFuture serverSocketFuture)
    {
        serverSocketFuture.addListener(future ->
        {
            final ChannelPipeline serverSocketPipeline = serverSocketFuture.channel().pipeline();

            // backup Netty's handler (ServerBootstrap$ServerBootstrapAcceptor) before removing it,
            // so we can use it later. it's registered by Netty in ServerBootstrap#init
            final ChannelInboundHandlerAdapter nettyChannelHandler = (ChannelInboundHandlerAdapter) serverSocketPipeline.removeFirst();

            serverSocketPipeline.addFirst(new WrappedChannelInitializer(nettyChannelHandler, context));
        });
    }

    @Override
    public Player extractPlayerFromChannel(final Object channelObj)
    {
        final Channel channel = (Channel) channelObj;

        final NetworkManager networkManager = (NetworkManager) channel.pipeline().get("packet_handler");
        if (networkManager.i() instanceof PlayerConnection)
        {
            final PlayerConnection playerConnection = (PlayerConnection) networkManager.i();
            return playerConnection.player.getBukkitEntity();
        }

        return null;
    }
}
