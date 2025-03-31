package ink.reactor.world.block.state;

public final class Comparator {
    public static int of(final char id, final BlockFacing facing, final BlockMode mode, final boolean powered) {
        final String sentence = facing+"-"+mode+"-"+powered;
        return switch(sentence) {
            case "NORTH-COMPARE-true" -> id-1;
            case "NORTH-SUBTRACT-true" -> id+1;
            case "NORTH-SUBTRACT-false" -> id+2;
            case "SOUTH-COMPARE-true" -> id+3;
            case "SOUTH-COMPARE-false" -> id+4;
            case "SOUTH-SUBTRACT-true" -> id+5;
            case "SOUTH-SUBTRACT-false" -> id+6;
            case "WEST-COMPARE-true" -> id+7;
            case "WEST-COMPARE-false" -> id+8;
            case "WEST-SUBTRACT-true" -> id+9;
            case "WEST-SUBTRACT-false" -> id+10;
            case "EAST-COMPARE-true" -> id+11;
            case "EAST-COMPARE-false" -> id+12;
            case "EAST-SUBTRACT-true" -> id+13;
            case "EAST-SUBTRACT-false" -> id+14;
            default -> id;
        };
    }
}