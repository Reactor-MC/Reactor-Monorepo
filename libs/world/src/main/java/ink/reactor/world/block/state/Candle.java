package ink.reactor.world.block.state;

public final class Candle {
    public static int of(final char id, final long candles, final boolean lit, final boolean waterlogged) {
        final String sentence = candles+"-"+lit+"-"+waterlogged;
        return switch(sentence) {
            case "1-true-true" -> id-3;
            case "1-true-false" -> id-2;
            case "1-false-true" -> id-1;
            case "2-true-true" -> id+1;
            case "2-true-false" -> id+2;
            case "2-false-true" -> id+3;
            case "2-false-false" -> id+4;
            case "3-true-true" -> id+5;
            case "3-true-false" -> id+6;
            case "3-false-true" -> id+7;
            case "3-false-false" -> id+8;
            case "4-true-true" -> id+9;
            case "4-true-false" -> id+10;
            case "4-false-true" -> id+11;
            case "4-false-false" -> id+12;
            default -> id;
        };
    }
}