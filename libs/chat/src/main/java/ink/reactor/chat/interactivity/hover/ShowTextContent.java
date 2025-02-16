package ink.reactor.chat.interactivity.hover;

import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.ColoredComponent;
import ink.reactor.chat.component.RawComponent;
import ink.reactor.nbt.NBT;

public final class ShowTextContent implements HoverContent {
    private ChatComponent chatComponent;

    public ShowTextContent(ColoredComponent coloredComponent) {
        this.chatComponent = coloredComponent;
    }

    public ShowTextContent(RawComponent rawComponent) {
        this.chatComponent = rawComponent;
    }

    @Override
    public void writeNBT(final NBT nbt) {
        chatComponent.writeNBT(nbt);
    }

    @Override
    public byte[] toJsonBytes() {
        return chatComponent.toJsonBytes();
    }
}
