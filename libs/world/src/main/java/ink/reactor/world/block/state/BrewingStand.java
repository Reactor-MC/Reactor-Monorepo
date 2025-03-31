package ink.reactor.world.block.state;

public final class BrewingStand {
    public static int of(final char id, final boolean hasBottle0, final boolean hasBottle1, final boolean hasBottle2) {
        final String sentence = hasBottle0+"-"+hasBottle1+"-"+hasBottle2;
        return switch(sentence) {
            case "true-true-true" -> id-7;
            case "true-true-false" -> id-6;
            case "true-false-true" -> id-5;
            case "true-false-false" -> id-4;
            case "false-true-true" -> id-3;
            case "false-true-false" -> id-2;
            case "false-false-true" -> id-1;
            default -> id;
        };
    }
}