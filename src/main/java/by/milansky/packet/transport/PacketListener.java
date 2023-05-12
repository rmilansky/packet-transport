package by.milansky.packet.transport;

import by.milansky.packet.transport.utility.PacketTransportUtility;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

/**
 * @author milansky
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class PacketListener<T> {

    @SneakyThrows
    public static void setValue(@NonNull Object object, @NonNull String fieldName, @NonNull Object value) {
        PacketTransportUtility.setValue(object, fieldName, value);
    }

    @SneakyThrows
    public static <A> A getValue(@NonNull Object object, @NonNull String fieldName) {
        return PacketTransportUtility.getValue(object, fieldName);
    }

    @SneakyThrows
    public static <A> A invoke(@NonNull Object object, @NonNull String methodName, Object... args) {
        return PacketTransportUtility.invoke(object, methodName, args);
    }

    public abstract void accept(@NonNull T t);
}
