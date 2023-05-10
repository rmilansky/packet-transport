package by.milansky.packet.transport.injectable;

import by.milansky.packet.transport.PacketListenerManager;
import io.netty.channel.Channel;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author milansky
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public sealed abstract class Injectable permits InjectableDecoder, InjectableEncoder {

    PacketListenerManager manager;

    public abstract void inject(@NonNull Channel channel);

}
