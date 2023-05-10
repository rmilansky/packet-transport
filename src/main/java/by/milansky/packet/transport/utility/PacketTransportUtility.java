package by.milansky.packet.transport.utility;

import by.milansky.packet.transport.PacketListener;
import io.netty.channel.Channel;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.bukkit.entity.Player;

/**
 * @author milansky
 */
@UtilityClass
public final class PacketTransportUtility {

    public Channel getChannel(@NonNull Player player) {
        return PacketListener.getValue(PacketListener.getValue(PacketListener.getValue(PacketListener.invoke(player, "getHandle"), "playerConnection"), "networkManager"), "channel");
    }

    @SneakyThrows
    public static void setValue(@NonNull Object object, @NonNull String fieldName, @NonNull Object value) {
        val field = object.getClass().getDeclaredField(fieldName);

        field.setAccessible(true);
        field.set(object, value);
        field.setAccessible(false);
    }

    @SneakyThrows
    public static <A> A getValue(@NonNull Object object, @NonNull String fieldName) {
        val field = object.getClass().getDeclaredField(fieldName);

        field.setAccessible(true);

        return (A) field.get(object);
    }

    @SneakyThrows
    public static <A> A invoke(@NonNull Object object, @NonNull String methodName, Object... args) {
        val method = object.getClass().getDeclaredMethod(methodName);

        method.setAccessible(true);

        return (A) method.invoke(object, args);
    }

}
