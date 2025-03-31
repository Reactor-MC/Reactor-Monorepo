package ink.reactor.world.block.state;

public final class RedstoneLamp {
    public static int of(final char id, final boolean lit) {
        final String sentence = String.valueOf(lit);
        return switch(sentence) {
            case "true" -> id-1;
            default -> id;
        };
    }
}