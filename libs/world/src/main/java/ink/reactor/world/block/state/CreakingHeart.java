package ink.reactor.world.block.state;

public final class CreakingHeart {
    public static int of(final char id, final boolean active, final BlockAxis axis, final boolean natural) {
        final String sentence = active+"-"+axis+"-"+natural;
        return switch(sentence) {
            case "true-X-true" -> id-9;
            case "true-X-false" -> id-8;
            case "true-Y-true" -> id-7;
            case "true-Y-false" -> id-6;
            case "true-Z-true" -> id-5;
            case "true-Z-false" -> id-4;
            case "false-X-true" -> id-3;
            case "false-X-false" -> id-2;
            case "false-Y-true" -> id-1;
            case "false-Z-true" -> id+1;
            case "false-Z-false" -> id+2;
            default -> id;
        };
    }
}