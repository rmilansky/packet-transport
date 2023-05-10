package by.milansky.packet.transport.injectable;

import by.milansky.packet.transport.PacketListener;
import by.milansky.packet.transport.PacketListenerManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.NonNull;
import org.bukkit.Bukkit;

import java.util.List;

/**
 * @author milansky
 */
public final class InjectableDecoder extends Injectable {
    public InjectableDecoder(PacketListenerManager manager) {
        super(manager);
    }

    @Override
    @SuppressWarnings("rawtypes, unchecked")
    public void inject(@NonNull Channel channel) {
        channel.pipeline().addAfter("decoder", "TransportDecoder", new MessageToMessageDecoder<>() {
            @Override
            protected void decode(ChannelHandlerContext channelHandlerContext, Object packet, List<Object> list) {
                for (PacketListener listener : manager.getListeners()) {
                    try {
                        listener.accept(packet);

                        Bukkit.broadcastMessage("accepted " + listener);
                    } catch (ClassCastException ignored) {
                        // do not match
                    }
                }

                list.add(packet);
            }
        });
    }
}
