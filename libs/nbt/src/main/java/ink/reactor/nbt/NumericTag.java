package ink.reactor.nbt;

public abstract class NumericTag extends TagNBT {

    public NumericTag(final Object key) {
        super(key);
    }

    public abstract byte toByte();
    public abstract short toShort();
    public abstract float toFloat();
    public abstract double toDouble();
    public abstract int toInt();
    public abstract long toLong();
}