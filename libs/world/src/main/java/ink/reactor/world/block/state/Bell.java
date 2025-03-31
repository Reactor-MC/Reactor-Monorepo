package ink.reactor.world.block.state;

public final class Bell {
    public static int of(final char id, final BlockAttachment attachment, final BlockFacing facing, final boolean powered) {
        final String sentence = attachment+"-"+facing+"-"+powered;
        return switch(sentence) {
            case "FLOOR-NORTH-true" -> id-1;
            case "FLOOR-SOUTH-true" -> id+1;
            case "FLOOR-SOUTH-false" -> id+2;
            case "FLOOR-WEST-true" -> id+3;
            case "FLOOR-WEST-false" -> id+4;
            case "FLOOR-EAST-true" -> id+5;
            case "FLOOR-EAST-false" -> id+6;
            case "CEILING-NORTH-true" -> id+7;
            case "CEILING-NORTH-false" -> id+8;
            case "CEILING-SOUTH-true" -> id+9;
            case "CEILING-SOUTH-false" -> id+10;
            case "CEILING-WEST-true" -> id+11;
            case "CEILING-WEST-false" -> id+12;
            case "CEILING-EAST-true" -> id+13;
            case "CEILING-EAST-false" -> id+14;
            case "SINGLE_WALL-NORTH-true" -> id+15;
            case "SINGLE_WALL-NORTH-false" -> id+16;
            case "SINGLE_WALL-SOUTH-true" -> id+17;
            case "SINGLE_WALL-SOUTH-false" -> id+18;
            case "SINGLE_WALL-WEST-true" -> id+19;
            case "SINGLE_WALL-WEST-false" -> id+20;
            case "SINGLE_WALL-EAST-true" -> id+21;
            case "SINGLE_WALL-EAST-false" -> id+22;
            case "DOUBLE_WALL-NORTH-true" -> id+23;
            case "DOUBLE_WALL-NORTH-false" -> id+24;
            case "DOUBLE_WALL-SOUTH-true" -> id+25;
            case "DOUBLE_WALL-SOUTH-false" -> id+26;
            case "DOUBLE_WALL-WEST-true" -> id+27;
            case "DOUBLE_WALL-WEST-false" -> id+28;
            case "DOUBLE_WALL-EAST-true" -> id+29;
            case "DOUBLE_WALL-EAST-false" -> id+30;
            default -> id;
        };
    }
}