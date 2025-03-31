package ink.reactor.world.block.state;

public final class TurtleEgg {
    public static int of(final char id, final long eggs, final long hatch) {
        final String sentence = eggs+"-"+hatch;
        return switch(sentence) {
            case "1-1" -> id+1;
            case "1-2" -> id+2;
            case "2-0" -> id+3;
            case "2-1" -> id+4;
            case "2-2" -> id+5;
            case "3-0" -> id+6;
            case "3-1" -> id+7;
            case "3-2" -> id+8;
            case "4-0" -> id+9;
            case "4-1" -> id+10;
            case "4-2" -> id+11;
            default -> id;
        };
    }
}