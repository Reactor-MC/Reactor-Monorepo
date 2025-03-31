package ink.reactor.world.block.state;

public final class SeaPickle {
    public static int of(final char id, final long pickles, final boolean waterlogged) {
        final String sentence = pickles+"-"+waterlogged;
        return switch(sentence) {
            case "1-false" -> id+1;
            case "2-true" -> id+2;
            case "2-false" -> id+3;
            case "3-true" -> id+4;
            case "3-false" -> id+5;
            case "4-true" -> id+6;
            case "4-false" -> id+7;
            default -> id;
        };
    }
}