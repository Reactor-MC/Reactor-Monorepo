package ink.reactor.world.block.state;

public final class LayeredCauldron {
    public static int of(final char id, final long level) {
        final String sentence = String.valueOf(level);
        return switch(sentence) {
            case "2" -> id+1;
            case "3" -> id+2;
            default -> id;
        };
    }
}