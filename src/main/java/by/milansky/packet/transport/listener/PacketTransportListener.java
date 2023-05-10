package by.milansky.packet.transport.listener;

import by.milansky.packet.transport.PacketListener;
import by.milansky.packet.transport.injectable.Injectable;
import io.netty.channel.Channel;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.entity.Player;
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

    private static Channel getChannel(@NonNull Player player) {
        return PacketListener.getValue(PacketListener.getValue(PacketListener.getValue(PacketListener.invoke(player, "getHandle"), "playerConnection"), "networkManager"), "channel");
    }

    @EventHandler
    private void onJoin(@NonNull PlayerJoinEvent event) {
        for (val injectable : injectables) injectable.inject(getChannel(event.getPlayer()));
    }

}
