package ink.reactor.item.component;

import java.util.Collection;

import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.RawComponent;
import ink.reactor.chat.util.ComponentCombiner;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

final class Chat {

    static void serializeText(final FriendlyBuffer buffer, final Object component) {
        if (component instanceof ChatComponent[] components) {
            NBTByteWriter.writeNBT(ComponentCombiner.toNBT(components), buffer);
            return;
        }

        final ChatComponent componentToWrite = (component instanceof ChatComponent chatComponent)
            ? chatComponent
            : new RawComponent(component.toString());

        NBTByteWriter.writeNBT(componentToWrite.toNBTFastAdd(), buffer);
    }

    static void serializeLore(final FriendlyBuffer buffer, final Object component) {
        if (component instanceof Collection collection) {
            buffer.writeVarInt(collection.size());
            for (final Object object : collection) {
                serializeText(buffer, object);
            }
            return;
        }

        if (component instanceof ChatComponent[] array) {
            buffer.writeVarInt(1);
            NBTByteWriter.writeNBT(ComponentCombiner.toNBT(array), buffer);
            return;
        }
    
        if (component instanceof Object[] array) {
            buffer.writeVarInt(array.length);
            for (final Object element : array) {
                serializeText(buffer, element);
            }
            return;
        }

        buffer.writeVarInt(1);
        NBTByteWriter.writeNBT(new RawComponent(component.toString()).toNBTFastAdd(), buffer);
    }
}
