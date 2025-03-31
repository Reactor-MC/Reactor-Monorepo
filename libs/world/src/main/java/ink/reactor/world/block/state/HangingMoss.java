package ink.reactor.world.block.state;

public final class HangingMoss {
    public static int of(final char id, final boolean tip) {
        final String sentence = String.valueOf(tip);
        return switch(sentence) {
            case "false" -> id+1;
            default -> id;
        };
    }
}