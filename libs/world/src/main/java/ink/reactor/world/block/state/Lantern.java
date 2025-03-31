package ink.reactor.world.block.state;

public final class Lantern {
    public static int of(final char id, final boolean hanging, final boolean waterlogged) {
        final String sentence = hanging+"-"+waterlogged;
        return switch(sentence) {
            case "true-true" -> id-3;
            case "true-false" -> id-2;
            case "false-true" -> id-1;
            default -> id;
        };
    }
}