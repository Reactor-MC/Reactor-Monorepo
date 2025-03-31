package ink.reactor.world.block.state;

public final class MangrovePropagule {
    public static int of(final char id, final long age, final boolean hanging, final long stage, final boolean waterlogged) {
        final String sentence = age+"-"+hanging+"-"+stage+"-"+waterlogged;
        return switch(sentence) {
            case "0-true-0-true" -> id-5;
            case "0-true-0-false" -> id-4;
            case "0-true-1-true" -> id-3;
            case "0-true-1-false" -> id-2;
            case "0-false-0-true" -> id-1;
            case "0-false-1-true" -> id+1;
            case "0-false-1-false" -> id+2;
            case "1-true-0-true" -> id+3;
            case "1-true-0-false" -> id+4;
            case "1-true-1-true" -> id+5;
            case "1-true-1-false" -> id+6;
            case "1-false-0-true" -> id+7;
            case "1-false-0-false" -> id+8;
            case "1-false-1-true" -> id+9;
            case "1-false-1-false" -> id+10;
            case "2-true-0-true" -> id+11;
            case "2-true-0-false" -> id+12;
            case "2-true-1-true" -> id+13;
            case "2-true-1-false" -> id+14;
            case "2-false-0-true" -> id+15;
            case "2-false-0-false" -> id+16;
            case "2-false-1-true" -> id+17;
            case "2-false-1-false" -> id+18;
            case "3-true-0-true" -> id+19;
            case "3-true-0-false" -> id+20;
            case "3-true-1-true" -> id+21;
            case "3-true-1-false" -> id+22;
            case "3-false-0-true" -> id+23;
            case "3-false-0-false" -> id+24;
            case "3-false-1-true" -> id+25;
            case "3-false-1-false" -> id+26;
            case "4-true-0-true" -> id+27;
            case "4-true-0-false" -> id+28;
            case "4-true-1-true" -> id+29;
            case "4-true-1-false" -> id+30;
            case "4-false-0-true" -> id+31;
            case "4-false-0-false" -> id+32;
            case "4-false-1-true" -> id+33;
            case "4-false-1-false" -> id+34;
            default -> id;
        };
    }
}