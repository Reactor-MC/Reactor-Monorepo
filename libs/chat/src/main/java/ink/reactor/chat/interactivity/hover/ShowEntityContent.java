package ink.reactor.chat.interactivity.hover;

import java.util.UUID;

import ink.reactor.nbt.NBT;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class ShowEntityContent implements HoverContent {

    private final String name;
    private final String type;
    private final UUID uuid;

    public ShowEntityContent(String type, UUID uuid) {
        this(null, type, uuid);
    }

    @Override
    public void writeNBT(NBT nbt) {
        if (name != null) {
            nbt.addString("name", name);
        }
        nbt.addString("type", type);
        nbt.addUUID("id", uuid);
    }

    @Override
    public byte[] toJsonBytes() {
        return toJson().getBytes();
    }

    @Override
    public String toJson() {
        final StringBuilder builder = new StringBuilder();
        builder.append("{\"type\":\"");
        builder.append(type);
        builder.append("\",\"id:\"");
        builder.append(uuid);
        builder.append('"');

        if (name != null) {
            builder.append(",\"name\":\"");
            builder.append(name);
            builder.append('"');
        }
        builder.append('}');

        return builder.toString();
    }
}