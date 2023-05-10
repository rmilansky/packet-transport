package by.milansky.packet.transport;

import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;
import lombok.NonNull;
import lombok.val;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author milansky
 */
public final class PacketListenerManager extends THashMap<JavaPlugin, Collection<PacketListener<?>>> {

    public void register(@NonNull JavaPlugin plugin, PacketListener<?>... packetListeners) {
        val collection = computeIfAbsent(plugin, __ -> new THashSet<>());

        collection.addAll(List.of(packetListeners));
    }

    public <T> void registerCompact(@NonNull JavaPlugin plugin, @NonNull Consumer<T> consumer) {
        register(plugin, new PacketListener<T>() {
            @Override
            public void accept(@NonNull T t) {
                consumer.accept(t);
            }
        });
    }

    public void unregister(@NonNull JavaPlugin plugin) {
        remove(plugin);
    }

    public Collection<PacketListener<?>> getListeners() {
        return values().stream().flatMap(Collection::stream).toList();
    }

}
