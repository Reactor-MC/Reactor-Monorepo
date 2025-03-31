package ink.reactor.world.block.state;

public final class Slab {
    public static int of(final char id, final BlockType type, final boolean waterlogged) {
        final String sentence = type+"-"+waterlogged;
        return switch(sentence) {
            case "TOP-true" -> id-3;
            case "TOP-false" -> id-2;
            case "BOTTOM-true" -> id-1;
            case "DOUBLE-true" -> id+1;
            case "DOUBLE-false" -> id+2;
            default -> id;
        };
    }
}