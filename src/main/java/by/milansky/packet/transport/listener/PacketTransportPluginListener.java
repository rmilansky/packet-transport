package by.milansky.packet.transport.listener;

import by.milansky.packet.transport.PacketListenerManager;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author milansky
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class PacketTransportPluginListener implements Listener {

    PacketListenerManager packetListenerManager;

    @EventHandler
    private void onPluginDisable(@NonNull PluginDisableEvent event) {
        packetListenerManager.unregister((JavaPlugin) event.getPlugin());
    }

}
