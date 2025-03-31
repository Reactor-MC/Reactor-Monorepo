package ink.reactor.world.block.state;

public final class SmallDripleaf {
    public static int of(final char id, final BlockFacing facing, final BlockHalf half, final boolean waterlogged) {
        final String sentence = facing+"-"+half+"-"+waterlogged;
        return switch(sentence) {
            case "NORTH-UPPER-true" -> id-3;
            case "NORTH-UPPER-false" -> id-2;
            case "NORTH-LOWER-true" -> id-1;
            case "SOUTH-UPPER-true" -> id+1;
            case "SOUTH-UPPER-false" -> id+2;
            case "SOUTH-LOWER-true" -> id+3;
            case "SOUTH-LOWER-false" -> id+4;
            case "WEST-UPPER-true" -> id+5;
            case "WEST-UPPER-false" -> id+6;
            case "WEST-LOWER-true" -> id+7;
            case "WEST-LOWER-false" -> id+8;
            case "EAST-UPPER-true" -> id+9;
            case "EAST-UPPER-false" -> id+10;
            case "EAST-LOWER-true" -> id+11;
            case "EAST-LOWER-false" -> id+12;
            default -> id;
        };
    }
}