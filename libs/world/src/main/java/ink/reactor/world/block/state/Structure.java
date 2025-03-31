package ink.reactor.world.block.state;

public final class Structure {
    public static int of(final char id, final BlockMode mode) {
        final String sentence = String.valueOf(mode);
        return switch(sentence) {
            case "SAVE" -> id-1;
            case "CORNER" -> id+1;
            case "DATA" -> id+2;
            default -> id;
        };
    }
}