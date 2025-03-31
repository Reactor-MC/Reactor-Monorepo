package ink.reactor.world.block.state;

public final class TallFlower {
    public static int of(final char id, final BlockHalf half) {
        final String sentence = String.valueOf(half);
        return switch(sentence) {
            case "UPPER" -> id-1;
            default -> id;
        };
    }
}