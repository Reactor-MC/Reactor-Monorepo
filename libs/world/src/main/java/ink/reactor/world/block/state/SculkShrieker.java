package ink.reactor.world.block.state;

public final class SculkShrieker {
    public static int of(final char id, final boolean canSummon, final boolean shrieking, final boolean waterlogged) {
        final String sentence = canSummon+"-"+shrieking+"-"+waterlogged;
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