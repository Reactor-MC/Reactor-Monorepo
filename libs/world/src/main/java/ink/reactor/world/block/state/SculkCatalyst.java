package ink.reactor.world.block.state;

public final class SculkCatalyst {
    public static int of(final char id, final boolean bloom) {
        final String sentence = String.valueOf(bloom);
        return switch(sentence) {
            case "true" -> id-1;
            default -> id;
        };
    }
}