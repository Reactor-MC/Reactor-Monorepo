package ink.reactor.world.block.state;

public final class PlayerHead {
    public static int of(final char id, final boolean powered, final long rotation) {
        final String sentence = powered+"-"+rotation;
        return switch(sentence) {
            case "true-0" -> id-16;
            case "true-1" -> id-15;
            case "true-2" -> id-14;
            case "true-3" -> id-13;
            case "true-4" -> id-12;
            case "true-5" -> id-11;
            case "true-6" -> id-10;
            case "true-7" -> id-9;
            case "true-8" -> id-8;
            case "true-9" -> id-7;
            case "true-10" -> id-6;
            case "true-11" -> id-5;
            case "true-12" -> id-4;
            case "true-13" -> id-3;
            case "true-14" -> id-2;
            case "true-15" -> id-1;
            case "false-1" -> id+1;
            case "false-2" -> id+2;
            case "false-3" -> id+3;
            case "false-4" -> id+4;
            case "false-5" -> id+5;
            case "false-6" -> id+6;
            case "false-7" -> id+7;
            case "false-8" -> id+8;
            case "false-9" -> id+9;
            case "false-10" -> id+10;
            case "false-11" -> id+11;
            case "false-12" -> id+12;
            case "false-13" -> id+13;
            case "false-14" -> id+14;
            case "false-15" -> id+15;
            default -> id;
        };
    }
}