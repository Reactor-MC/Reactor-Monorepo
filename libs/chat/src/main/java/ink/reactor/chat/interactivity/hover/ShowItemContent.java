package ink.reactor.chat.interactivity.hover;

import ink.reactor.nbt.NBT;
import ink.reactor.nbt.writer.NBTStringWriter;

public final class ShowItemContent implements HoverContent {

    private final String id;
    private final int count;
    private final NBT components;

    public ShowItemContent(String id) {
        this(id, 0, null);
    }

    public ShowItemContent(String id, int count) {
        this(id, count, null);
    }

    public ShowItemContent(String id, int count, NBT components) {
        this.id = id;
        this.count = count;
        this.components = components;
    }

    @Override
    public void writeNBT(final NBT nbt) {
        nbt.addString("id", id);
        if (count > 0) {
            nbt.addInt("count", count);
        }
        if (components != null && !components.getTags().isEmpty()) {
            nbt.addCompound("components", components);
        }
    }

    @Override
    public byte[] toJsonBytes() {
        return toJson().getBytes();
    }

    @Override
    public String toJson() {
        final StringBuilder buffer = new StringBuilder();

        buffer.append("{\"id\":\"");
        buffer.append(id);
        buffer.append('"');
        if (count > 0) {
            buffer.append(",\"count\":");
            buffer.append(count);
        }
        if (components != null && !components.getTags().isEmpty()) {
            buffer.append(",\"components\":");
            buffer.append(NBTStringWriter.toJson(components));
        }
        buffer.append('}');
        return buffer.toString();
    }
}
