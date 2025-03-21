package ink.reactor.chat.component;

import ink.reactor.nbt.NBT;
import ink.reactor.nbt.type.NBTFastAdd;
import ink.reactor.nbt.type.NBTGeneral;

public interface ChatComponent {
    byte
        TRUE = 2,
        FALSE = 1,
        UNDEFINED = 0;

    ChatComponent NEW_LINE = new RawComponent("\n");
    ChatComponent EMPTY = new RawComponent("");

    byte[] toJsonBytes();

    default String toJson() {
        return new String(toJsonBytes());
    }

    void writeNBT(final NBT nbt);

    default NBT toNBT() {
        final NBTGeneral nbtGeneral = new NBTGeneral();
        writeNBT(nbtGeneral);
        return nbtGeneral;
    }

    default NBT toNBTFastAdd() {
        final NBTFastAdd nbtFastAdd = new NBTFastAdd();
        writeNBT(nbtFastAdd);
        return nbtFastAdd;
    }

    ChatComponent copy();

    String getText();
    void setText(final String newText);
    void setToDefault();
}