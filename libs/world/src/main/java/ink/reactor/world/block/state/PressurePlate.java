package ink.reactor.world.block.state;

public final class PressurePlate {
    public static int of(final char id, final boolean powered) {
        final String sentence = String.valueOf(powered);
        return switch(sentence) {
            case "true" -> id-1;
            default -> id;
        };
    }
}