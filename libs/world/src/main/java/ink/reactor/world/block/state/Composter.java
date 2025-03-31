package ink.reactor.world.block.state;

public final class Composter {
    public static int of(final char id, final long level) {
        final String sentence = String.valueOf(level);
        return switch(sentence) {
            case "1" -> id+1;
            case "2" -> id+2;
            case "3" -> id+3;
            case "4" -> id+4;
            case "5" -> id+5;
            case "6" -> id+6;
            case "7" -> id+7;
            case "8" -> id+8;
            default -> id;
        };
    }
}