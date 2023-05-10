package by.milansky.packet.transport;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author milansky
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class PacketListener<T> {

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

    public abstract void accept(@NonNull T t);
}
