package ink.reactor.world.block.state;

public final class Grass {
    public static int of(final char id, final boolean snowy) {
        final String sentence = String.valueOf(snowy);
        return switch(sentence) {
            case "true" -> id-1;
            default -> id;
        };
    }
}