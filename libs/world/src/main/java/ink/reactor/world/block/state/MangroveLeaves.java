package ink.reactor.world.block.state;

public final class MangroveLeaves {
    public static int of(final char id, final long distance, final boolean persistent, final boolean waterlogged) {
        final String sentence = distance+"-"+persistent+"-"+waterlogged;
        return switch(sentence) {
            case "1-true-true" -> id-27;
            case "1-true-false" -> id-26;
            case "1-false-true" -> id-25;
            case "1-false-false" -> id-24;
            case "2-true-true" -> id-23;
            case "2-true-false" -> id-22;
            case "2-false-true" -> id-21;
            case "2-false-false" -> id-20;
            case "3-true-true" -> id-19;
            case "3-true-false" -> id-18;
            case "3-false-true" -> id-17;
            case "3-false-false" -> id-16;
            case "4-true-true" -> id-15;
            case "4-true-false" -> id-14;
            case "4-false-true" -> id-13;
            case "4-false-false" -> id-12;
            case "5-true-true" -> id-11;
            case "5-true-false" -> id-10;
            case "5-false-true" -> id-9;
            case "5-false-false" -> id-8;
            case "6-true-true" -> id-7;
            case "6-true-false" -> id-6;
            case "6-false-true" -> id-5;
            case "6-false-false" -> id-4;
            case "7-true-true" -> id-3;
            case "7-true-false" -> id-2;
            case "7-false-true" -> id-1;
            default -> id;
        };
    }
}