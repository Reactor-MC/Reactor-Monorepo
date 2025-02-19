package ink.reactor.item.deserializer;

import ink.reactor.item.ItemStack;
import ink.reactor.item.Material;
import ink.reactor.item.component.ItemComponentDeserializer;
import ink.reactor.util.buffer.reader.ReadBuffer;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ItemStackByteDeserializer {

    public static ItemStack readItemStack(final ReadBuffer buffer) {
        final int amount = buffer.readVarInt();
        final int material = buffer.readVarInt();
        final int componentCount = buffer.readVarInt();
        final int componentsToRemove = buffer.readVarInt();
       
        final ItemStack itemStack = new ItemStack(Material.byId(material), amount);

        if (componentCount > 0) {
            itemStack.setComponents(ItemComponentDeserializer.deserialize(buffer, componentCount));
        }
        if (componentsToRemove > 0) {
            final byte[] arrayComponents = new byte[componentsToRemove];
            for (int i = 0; i < componentsToRemove; i++) {
                arrayComponents[i] = buffer.readByte();
            }
            itemStack.setComponentsToRemove(arrayComponents);
        }
        return itemStack;
    }
}
