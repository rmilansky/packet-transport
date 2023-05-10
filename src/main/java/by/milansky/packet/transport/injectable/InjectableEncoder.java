package by.milansky.packet.transport.injectable;

import by.milansky.packet.transport.PacketListener;
import by.milansky.packet.transport.PacketListenerManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.NonNull;

import java.util.List;

/**
 * @author milansky
 */
public final class InjectableEncoder extends Injectable {
    public InjectableEncoder(PacketListenerManager manager) {
        super(manager);
    }

    @Override
    @SuppressWarnings("rawtypes, unchecked")
    public void inject(@NonNull Channel channel) {
        channel.pipeline().addAfter("encoder", "TransportEncoder", new MessageToMessageEncoder<>() {
            @Override
            protected void encode(ChannelHandlerContext channelHandlerContext, Object packet, List<Object> list) {
                for (PacketListener listener : manager.getListeners()) {
                    try {
                        listener.accept(packet);
                    } catch (ClassCastException ignored) {
                        // do not match
                    }
                }

                list.add(packet);
            }
        });
    }
}
