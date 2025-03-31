package ink.reactor.world.block.state;

public final class BubbleColumn {
    public static int of(final char id, final boolean drag) {
        final String sentence = String.valueOf(drag);
        return switch(sentence) {
            case "false" -> id+1;
            default -> id;
        };
    }
}