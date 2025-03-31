package ink.reactor.world.block.state;

public final class IronBars {
    public static int of(final char id, final boolean east, final boolean north, final boolean south, final boolean waterlogged, final boolean west) {
        final String sentence = east+"-"+north+"-"+south+"-"+waterlogged+"-"+west;
        return switch(sentence) {
            case "true-true-true-true-true" -> id-31;
            case "true-true-true-true-false" -> id-30;
            case "true-true-true-false-true" -> id-29;
            case "true-true-true-false-false" -> id-28;
            case "true-true-false-true-true" -> id-27;
            case "true-true-false-true-false" -> id-26;
            case "true-true-false-false-true" -> id-25;
            case "true-true-false-false-false" -> id-24;
            case "true-false-true-true-true" -> id-23;
            case "true-false-true-true-false" -> id-22;
            case "true-false-true-false-true" -> id-21;
            case "true-false-true-false-false" -> id-20;
            case "true-false-false-true-true" -> id-19;
            case "true-false-false-true-false" -> id-18;
            case "true-false-false-false-true" -> id-17;
            case "true-false-false-false-false" -> id-16;
            case "false-true-true-true-true" -> id-15;
            case "false-true-true-true-false" -> id-14;
            case "false-true-true-false-true" -> id-13;
            case "false-true-true-false-false" -> id-12;
            case "false-true-false-true-true" -> id-11;
            case "false-true-false-true-false" -> id-10;
            case "false-true-false-false-true" -> id-9;
            case "false-true-false-false-false" -> id-8;
            case "false-false-true-true-true" -> id-7;
            case "false-false-true-true-false" -> id-6;
            case "false-false-true-false-true" -> id-5;
            case "false-false-true-false-false" -> id-4;
            case "false-false-false-true-true" -> id-3;
            case "false-false-false-true-false" -> id-2;
            case "false-false-false-false-true" -> id-1;
            default -> id;
        };
    }
}