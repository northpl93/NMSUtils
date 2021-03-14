package pl.north93.nmsutils.protocol;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import pl.north93.nmsutils.protocol.event.ChannelInitializeEvent;

@Slf4j
@ToString
@AllArgsConstructor
class NorthChannelInitializer extends ChannelInitializer<Channel>
{
    private final InjectorContext context;

    @Override
    protected void initChannel(final Channel channel)
    {
        log.info("initialising channel: {}", channel);
        this.context.callEvent(new ChannelInitializeEvent(channel));
        channel.pipeline().addBefore("packet_handler", "north_packet_handler", new NorthChannelHandler(this.context));
    }
}
