# packet-transport
A lightweight bukkit library that allows to change incoming/outcoming packets in runtime.
## HOW TO USE?

To start with Packet Transport, you should create class inherited from PacketListener<T>, <br>
where T -- packet you want to modify in runtime.

Using example:

```java
import lombok.NonNull;
import by.milansky.packet.transport.PacketListener;
import by.milansky.packet.transport.utility.PacketTransportUtility;
import net.minecraft.server.v1_8_R3.PacketPlayInChat;

public final class TestListener extends PacketListener<PacketPlayInChat> {
    @Override
    public void accept(@NonNull PacketPlayInChat packet) {
        // reading message sent by client from packet
        String message = getValue(packet, "a");

        // return, cuz it is a comman
        if (message.startsWith("/")) return;

        // setting message in packet by reflection
        PacketTransportUtility.setValue(packet, "a", "https://github.com/rmilansky/packet-transport");
    }
}

```
This code will handle all packets sent by client with type "PacketPlayInChat" and change message in it.<br>
But you need to register this listener in the main class of your plugin:

```java
PacketTransportPlugin.getPlugin(PacketTransportPlugin.class).getListenerManager().register(this, new TestListener());
```
![Chat](https://i.imgur.com/rXEraSV.png)