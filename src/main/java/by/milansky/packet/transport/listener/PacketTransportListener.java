package by.milansky.packet.transport.listener;

import by.milansky.packet.transport.injectable.Injectable;
import by.milansky.packet.transport.utility.PacketTransportUtility;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author milansky
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class PacketTransportListener implements Listener {

    Injectable[] injectables;

    public PacketTransportListener(Injectable... injectables) {
        this.injectables = injectables;
    }

    @EventHandler
    private void onJoin(@NonNull PlayerJoinEvent event) {
        for (val injectable : injectables) injectable.inject(PacketTransportUtility.getChannel(event.getPlayer()));
    }

}
