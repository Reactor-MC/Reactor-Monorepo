package ink.reactor.world.block.state;

public final class Chest {
    public static int of(final char id, final BlockType type, final BlockFacing facing, final boolean waterlogged) {
        final String sentence = type+"-"+facing+"-"+waterlogged;
        return switch(sentence) {
            case "SINGLE-NORTH-true" -> id-1;
            case "LEFT-NORTH-true" -> id+1;
            case "LEFT-NORTH-false" -> id+2;
            case "RIGHT-NORTH-true" -> id+3;
            case "RIGHT-NORTH-false" -> id+4;
            case "SINGLE-SOUTH-true" -> id+5;
            case "SINGLE-SOUTH-false" -> id+6;
            case "LEFT-SOUTH-true" -> id+7;
            case "LEFT-SOUTH-false" -> id+8;
            case "RIGHT-SOUTH-true" -> id+9;
            case "RIGHT-SOUTH-false" -> id+10;
            case "SINGLE-WEST-true" -> id+11;
            case "SINGLE-WEST-false" -> id+12;
            case "LEFT-WEST-true" -> id+13;
            case "LEFT-WEST-false" -> id+14;
            case "RIGHT-WEST-true" -> id+15;
            case "RIGHT-WEST-false" -> id+16;
            case "SINGLE-EAST-true" -> id+17;
            case "SINGLE-EAST-false" -> id+18;
            case "LEFT-EAST-true" -> id+19;
            case "LEFT-EAST-false" -> id+20;
            case "RIGHT-EAST-true" -> id+21;
            case "RIGHT-EAST-false" -> id+22;
            default -> id;
        };
    }
}