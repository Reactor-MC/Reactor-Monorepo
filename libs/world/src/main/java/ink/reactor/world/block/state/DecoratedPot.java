package ink.reactor.world.block.state;

public final class DecoratedPot {
    public static int of(final char id, final boolean cracked, final BlockFacing facing, final boolean waterlogged) {
        final String sentence = cracked+"-"+facing+"-"+waterlogged;
        return switch(sentence) {
            case "true-NORTH-true" -> id-9;
            case "true-NORTH-false" -> id-8;
            case "true-SOUTH-true" -> id-7;
            case "true-SOUTH-false" -> id-6;
            case "true-WEST-true" -> id-5;
            case "true-WEST-false" -> id-4;
            case "true-EAST-true" -> id-3;
            case "true-EAST-false" -> id-2;
            case "false-NORTH-true" -> id-1;
            case "false-SOUTH-true" -> id+1;
            case "false-SOUTH-false" -> id+2;
            case "false-WEST-true" -> id+3;
            case "false-WEST-false" -> id+4;
            case "false-EAST-true" -> id+5;
            case "false-EAST-false" -> id+6;
            default -> id;
        };
    }
}