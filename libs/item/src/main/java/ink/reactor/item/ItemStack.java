package ink.reactor.item;

import ink.reactor.item.serializer.ItemStackStringSerializer;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ItemStack {
    private Byte2ObjectOpenHashMap<Object> components;
    private byte[] componentsToRemove;

    private Material material;
    private byte amount = 1;

    public ItemStack(Material material) {
        this.material = material;
    }

    public ItemStack(Material material, int amount) {
        this.material = material;
        this.amount = (byte)amount;
    }

    public boolean hasComponents() {
        return components != null && !components.isEmpty();
    }

    public Byte2ObjectOpenHashMap<Object> getComponents() {
        if (components == null) {
            components = new Byte2ObjectOpenHashMap<>();
        }
        return components;
    }

    public boolean fastEqual(final ItemStack otherItem) {
        return otherItem.material == this.material && otherItem.amount == this.amount;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ItemStack otherItem)) {
            return false;
        }
        if (!fastEqual(otherItem)) {
            return false;
        }
        if (otherItem.hasComponents()) {
            return otherItem.components.equals(this.components);
        }
        if (this.hasComponents()) {
            return components.equals(otherItem.components);
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (amount << 24) | material.getId();
    }

    @Override
    public String toString() {
        return ItemStackStringSerializer.toRedeableJson(this);
    }
}