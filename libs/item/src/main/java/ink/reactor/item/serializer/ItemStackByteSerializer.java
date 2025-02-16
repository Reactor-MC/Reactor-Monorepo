package ink.reactor.item.serializer;

import ink.reactor.item.ItemStack;
import ink.reactor.item.component.ItemComponentSerializer;
import ink.reactor.util.buffer.writer.FriendlyBuffer;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ItemStackByteSerializer {

    public static void toBytes(final ItemStack itemStack, final FriendlyBuffer buffer) {
        final int componentCount = itemStack.hasComponents() ? itemStack.getComponents().size() : 0;

        buffer.writeVarInt(itemStack.getAmount());
        buffer.writeVarInt(itemStack.getMaterial().getId());

        buffer.writeVarInt(componentCount);
        buffer.writeVarInt(itemStack.getComponentsToRemove() == null ? 0 : itemStack.getComponentsToRemove().length);

        if (componentCount > 0) {
            ItemComponentSerializer.serialize(itemStack.getComponents(), buffer);
        }

        if (itemStack.getComponentsToRemove() != null) {
            buffer.writeBytes(itemStack.getComponentsToRemove());
        }
    }
}