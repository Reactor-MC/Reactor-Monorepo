package ink.reactor.world.block.state;

public final class CaveVinesPlant {
    public static int of(final char id, final boolean berries) {
        final String sentence = String.valueOf(berries);
        return switch(sentence) {
            case "true" -> id-1;
            default -> id;
        };
    }
}