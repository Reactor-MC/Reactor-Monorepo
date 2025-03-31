package ink.reactor.world.block.state;

public final class PitcherCrop {
    public static int of(final char id, final long age, final BlockHalf half) {
        final String sentence = age+"-"+half;
        return switch(sentence) {
            case "0-UPPER" -> id-1;
            case "1-UPPER" -> id+1;
            case "1-LOWER" -> id+2;
            case "2-UPPER" -> id+3;
            case "2-LOWER" -> id+4;
            case "3-UPPER" -> id+5;
            case "3-LOWER" -> id+6;
            case "4-UPPER" -> id+7;
            case "4-LOWER" -> id+8;
            default -> id;
        };
    }
}