package ink.reactor.chat.component;

import ink.reactor.chat.component.serializer.JsonComponentSerializer;
import ink.reactor.nbt.NBT;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class RawComponent implements ChatComponent {

    private String text;

    public RawComponent(String text) {
        this.text = text;
    }

    @Override
    public String toJson() {
        return "{\"text\":\"" + text + "\"}";
    }

    @Override
    public byte[] toJsonBytes() {
        return JsonComponentSerializer.toJson(this);
    }

    @Override
    public void writeNBT(NBT nbt) {
        nbt.addString("text", text);
    }

    @Override
    public ChatComponent copy() {
        return new RawComponent(text);
    }

    @Override
    public String toString() {
        return toJson();
    }
}