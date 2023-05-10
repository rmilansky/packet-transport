package by.milansky.packet.transport;

import by.milansky.packet.transport.injectable.InjectableDecoder;
import by.milansky.packet.transport.injectable.InjectableEncoder;
import by.milansky.packet.transport.listener.PacketTransportListener;
import by.milansky.packet.transport.listener.PacketTransportPluginListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

/**
 * @author milansky
 */
@Author("PbIBa (milansky)")
@Plugin(name = "PacketTransport", version = "1.0.0-BETA")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class PacketTransportPlugin extends JavaPlugin {

    @Getter
    PacketListenerManager listenerManager = new PacketListenerManager();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PacketTransportListener(
                new InjectableDecoder(listenerManager),
                new InjectableEncoder(listenerManager)
        ), this);
        Bukkit.getPluginManager().registerEvents(new PacketTransportPluginListener(listenerManager), this);
    }

}
