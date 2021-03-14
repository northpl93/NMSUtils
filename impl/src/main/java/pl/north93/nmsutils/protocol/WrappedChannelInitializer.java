package pl.north93.nmsutils.protocol;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
class WrappedChannelInitializer extends ChannelInboundHandlerAdapter
{
    private final ChannelInboundHandlerAdapter originalChannelHandler;
    private final InjectorContext context;

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception
    {
        // firstly, execute Netty's handler so it can setup its shit and call
        // Minecraft's childHandler (from ServerConnection.class)
        this.originalChannelHandler.channelRead(ctx, msg);

        final Channel childChannel = (Channel) msg;
        // then add our own initializer to client's channel pipeline
        childChannel.pipeline().addLast(new NorthChannelInitializer(this.context));
    }
}
