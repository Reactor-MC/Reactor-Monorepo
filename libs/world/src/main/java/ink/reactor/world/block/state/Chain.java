package ink.reactor.world.block.state;

public final class Chain {
    public static int of(final char id, final BlockAxis axis, final boolean waterlogged) {
        final String sentence = axis+"-"+waterlogged;
        return switch(sentence) {
            case "X-true" -> id-3;
            case "X-false" -> id-2;
            case "Y-true" -> id-1;
            case "Z-true" -> id+1;
            case "Z-false" -> id+2;
            default -> id;
        };
    }
}