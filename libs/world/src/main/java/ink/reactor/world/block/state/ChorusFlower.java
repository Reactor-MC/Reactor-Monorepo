package ink.reactor.world.block.state;

public final class ChorusFlower {
    public static int of(final char id, final long age) {
        final String sentence = String.valueOf(age);
        return switch(sentence) {
            case "1" -> id+1;
            case "2" -> id+2;
            case "3" -> id+3;
            case "4" -> id+4;
            case "5" -> id+5;
            default -> id;
        };
    }
}