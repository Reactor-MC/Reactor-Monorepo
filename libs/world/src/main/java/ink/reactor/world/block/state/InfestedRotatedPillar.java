package ink.reactor.world.block.state;

public final class InfestedRotatedPillar {
    public static int of(final char id, final BlockAxis axis) {
        final String sentence = String.valueOf(axis);
        return switch(sentence) {
            case "X" -> id-1;
            case "Z" -> id+1;
            default -> id;
        };
    }
}