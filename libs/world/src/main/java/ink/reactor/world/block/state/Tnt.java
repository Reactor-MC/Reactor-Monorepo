package ink.reactor.world.block.state;

public final class Tnt {
    public static int of(final char id, final boolean unstable) {
        final String sentence = String.valueOf(unstable);
        return switch(sentence) {
            case "true" -> id-1;
            default -> id;
        };
    }
}