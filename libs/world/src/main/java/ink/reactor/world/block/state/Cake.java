package ink.reactor.world.block.state;

public final class Cake {
    public static int of(final char id, final long bites) {
        final String sentence = String.valueOf(bites);
        return switch(sentence) {
            case "1" -> id+1;
            case "2" -> id+2;
            case "3" -> id+3;
            case "4" -> id+4;
            case "5" -> id+5;
            case "6" -> id+6;
            default -> id;
        };
    }
}