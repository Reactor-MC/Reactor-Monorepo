package ink.reactor.item.data;

public record CustomModelData(
    float[] floats,
    boolean[] flags,
    String[] strings,
    int[] colors
) {}