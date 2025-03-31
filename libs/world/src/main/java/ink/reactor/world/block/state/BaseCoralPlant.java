package ink.reactor.world.block.state;

public final class BaseCoralPlant {
    public static int of(final char id, final boolean waterlogged) {
        final String sentence = String.valueOf(waterlogged);
        return switch(sentence) {
            case "false" -> id+1;
            default -> id;
        };
    }
}