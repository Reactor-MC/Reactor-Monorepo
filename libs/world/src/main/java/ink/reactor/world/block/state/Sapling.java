package ink.reactor.world.block.state;

public final class Sapling {
    public static int of(final char id, final long stage) {
        final String sentence = String.valueOf(stage);
        return switch(sentence) {
            case "1" -> id+1;
            default -> id;
        };
    }
}