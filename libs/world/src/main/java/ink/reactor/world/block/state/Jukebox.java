package ink.reactor.world.block.state;

public final class Jukebox {
    public static int of(final char id, final boolean hasRecord) {
        final String sentence = String.valueOf(hasRecord);
        return switch(sentence) {
            case "true" -> id-1;
            default -> id;
        };
    }
}