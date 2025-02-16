package ink.reactor.chat.interactivity.hover;

import ink.reactor.nbt.NBT;

public sealed interface HoverContent permits ShowTextContent, ShowItemContent, ShowEntityContent {

    void writeNBT(final NBT nbt);

    byte[] toJsonBytes();

    default String toJson() {
        return new String(toJsonBytes());
    }
}
