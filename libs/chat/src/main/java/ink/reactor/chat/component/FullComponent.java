package ink.reactor.chat.component;


import ink.reactor.chat.ChatColor;
import ink.reactor.chat.component.serializer.JsonComponentSerializer;
import ink.reactor.chat.interactivity.ClickEvent;
import ink.reactor.chat.interactivity.HoverEvent;
import ink.reactor.nbt.NBT;
import ink.reactor.nbt.type.NBTGeneral;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class FullComponent extends ColoredComponent {

    private ClickEvent clickEvent;
    private HoverEvent hoverEvent;

    public FullComponent(String text) {
        super(text);
    }

    public FullComponent(String text, ChatColor color) {
        super(text, color);
    }

    @Override
    public void writeNBT(final NBT nbt) {
        super.writeNBT(nbt);

        if (clickEvent != null) {
            final NBTGeneral clickevent = new NBTGeneral();
            clickevent.addString("action", this.clickEvent.action().getId());
            clickevent.addString("value", this.clickEvent.value());
            nbt.addCompound("clickEvent", clickevent);
        }

        if (hoverEvent != null) {
            final NBTGeneral hoverNBT = new NBTGeneral();
            hoverNBT.addString("action", hoverEvent.getAction().getId());

            final NBTGeneral contents = new NBTGeneral();
            hoverEvent.getContent().writeNBT(contents);
            hoverNBT.addCompound("contents", contents);

            nbt.addCompound("hoverEvent", hoverNBT);
        }
    }

    @Override
    public byte[] toJsonBytes() {
        return JsonComponentSerializer.toJson(this);
    }

    @Override
    public String toString() {
        return toJson();
    }

    @Override
    public void setToDefault() {
        super.setToDefault();
        clickEvent = null;
        hoverEvent = null;
    }
}