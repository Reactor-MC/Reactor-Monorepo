package ink.reactor.world.block.state;

public final class Bed {
    public static int of(final char id, final BlockFacing facing, final boolean occupied, final BlockPart part) {
        final String sentence = facing+"-"+occupied+"-"+part;
        return switch(sentence) {
            case "NORTH-true-HEAD" -> id-3;
            case "NORTH-true-FOOT" -> id-2;
            case "NORTH-false-HEAD" -> id-1;
            case "SOUTH-true-HEAD" -> id+1;
            case "SOUTH-true-FOOT" -> id+2;
            case "SOUTH-false-HEAD" -> id+3;
            case "SOUTH-false-FOOT" -> id+4;
            case "WEST-true-HEAD" -> id+5;
            case "WEST-true-FOOT" -> id+6;
            case "WEST-false-HEAD" -> id+7;
            case "WEST-false-FOOT" -> id+8;
            case "EAST-true-HEAD" -> id+9;
            case "EAST-true-FOOT" -> id+10;
            case "EAST-false-HEAD" -> id+11;
            case "EAST-false-FOOT" -> id+12;
            default -> id;
        };
    }
}