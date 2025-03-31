package ink.reactor.world.block.state;

public final class SnowLayer {
    public static int of(final char id, final long layers) {
        final String sentence = String.valueOf(layers);
        return switch(sentence) {
            case "2" -> id+1;
            case "3" -> id+2;
            case "4" -> id+3;
            case "5" -> id+4;
            case "6" -> id+5;
            case "7" -> id+6;
            case "8" -> id+7;
            default -> id;
        };
    }
}