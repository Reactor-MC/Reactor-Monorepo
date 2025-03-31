package ink.reactor.world.block.state;

public final class SnifferEgg {
    public static int of(final char id, final long hatch) {
        final String sentence = String.valueOf(hatch);
        return switch(sentence) {
            case "1" -> id+1;
            case "2" -> id+2;
            default -> id;
        };
    }
}