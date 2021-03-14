package pl.north93.nmsutils.protocol;

import org.bukkit.entity.Player;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
class NorthChannelHandler extends ChannelDuplexHandler
{
    private final InjectorContext injectorContext;

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception
    {
        final Player player = this.injectorContext.getPlayerFromChannel(ctx.channel());

        if (this.injectorContext.onPacketReceive(player, msg))
        {
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void write(final ChannelHandlerContext ctx, final Object msg, final ChannelPromise promise) throws Exception
    {
        final Player player = this.injectorContext.getPlayerFromChannel(ctx.channel());

        if (this.injectorContext.onPacketSend(player, msg))
        {
            super.write(ctx, msg, promise);
        }
    }
}
