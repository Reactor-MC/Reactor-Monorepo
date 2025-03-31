package ink.reactor.world.block.state;

public final class Kelp {
    public static int of(final char id, final long age) {
        final String sentence = String.valueOf(age);
        return switch(sentence) {
            case "1" -> id+1;
            case "2" -> id+2;
            case "3" -> id+3;
            case "4" -> id+4;
            case "5" -> id+5;
            case "6" -> id+6;
            case "7" -> id+7;
            case "8" -> id+8;
            case "9" -> id+9;
            case "10" -> id+10;
            case "11" -> id+11;
            case "12" -> id+12;
            case "13" -> id+13;
            case "14" -> id+14;
            case "15" -> id+15;
            case "16" -> id+16;
            case "17" -> id+17;
            case "18" -> id+18;
            case "19" -> id+19;
            case "20" -> id+20;
            case "21" -> id+21;
            case "22" -> id+22;
            case "23" -> id+23;
            case "24" -> id+24;
            case "25" -> id+25;
            default -> id;
        };
    }
}