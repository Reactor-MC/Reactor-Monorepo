package ink.reactor.world.block.state;

public final class RespawnAnchor {
    public static int of(final char id, final long charges) {
        final String sentence = String.valueOf(charges);
        return switch(sentence) {
            case "1" -> id+1;
            case "2" -> id+2;
            case "3" -> id+3;
            case "4" -> id+4;
            default -> id;
        };
    }
}