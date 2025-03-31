package ink.reactor.world.block.state;

public final class CopperBulbBlock {
    public static int of(final char id, final boolean lit, final boolean powered) {
        final String sentence = lit+"-"+powered;
        return switch(sentence) {
            case "true-true" -> id-3;
            case "true-false" -> id-2;
            case "false-true" -> id-1;
            default -> id;
        };
    }
}