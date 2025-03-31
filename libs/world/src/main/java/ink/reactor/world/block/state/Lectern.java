package ink.reactor.world.block.state;

public final class Lectern {
    public static int of(final char id, final BlockFacing facing, final boolean hasBook, final boolean powered) {
        final String sentence = facing+"-"+hasBook+"-"+powered;
        return switch(sentence) {
            case "NORTH-true-true" -> id-3;
            case "NORTH-true-false" -> id-2;
            case "NORTH-false-true" -> id-1;
            case "SOUTH-true-true" -> id+1;
            case "SOUTH-true-false" -> id+2;
            case "SOUTH-false-true" -> id+3;
            case "SOUTH-false-false" -> id+4;
            case "WEST-true-true" -> id+5;
            case "WEST-true-false" -> id+6;
            case "WEST-false-true" -> id+7;
            case "WEST-false-false" -> id+8;
            case "EAST-true-true" -> id+9;
            case "EAST-true-false" -> id+10;
            case "EAST-false-true" -> id+11;
            case "EAST-false-false" -> id+12;
            default -> id;
        };
    }
}