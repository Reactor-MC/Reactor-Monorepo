package ink.reactor.world.block.state;

public final class FenceGate {
    public static int of(final char id, final BlockFacing facing, final boolean inWall, final boolean open, final boolean powered) {
        final String sentence = facing+"-"+inWall+"-"+open+"-"+powered;
        return switch(sentence) {
            case "NORTH-true-true-true" -> id-7;
            case "NORTH-true-true-false" -> id-6;
            case "NORTH-true-false-true" -> id-5;
            case "NORTH-true-false-false" -> id-4;
            case "NORTH-false-true-true" -> id-3;
            case "NORTH-false-true-false" -> id-2;
            case "NORTH-false-false-true" -> id-1;
            case "SOUTH-true-true-true" -> id+1;
            case "SOUTH-true-true-false" -> id+2;
            case "SOUTH-true-false-true" -> id+3;
            case "SOUTH-true-false-false" -> id+4;
            case "SOUTH-false-true-true" -> id+5;
            case "SOUTH-false-true-false" -> id+6;
            case "SOUTH-false-false-true" -> id+7;
            case "SOUTH-false-false-false" -> id+8;
            case "WEST-true-true-true" -> id+9;
            case "WEST-true-true-false" -> id+10;
            case "WEST-true-false-true" -> id+11;
            case "WEST-true-false-false" -> id+12;
            case "WEST-false-true-true" -> id+13;
            case "WEST-false-true-false" -> id+14;
            case "WEST-false-false-true" -> id+15;
            case "WEST-false-false-false" -> id+16;
            case "EAST-true-true-true" -> id+17;
            case "EAST-true-true-false" -> id+18;
            case "EAST-true-false-true" -> id+19;
            case "EAST-true-false-false" -> id+20;
            case "EAST-false-true-true" -> id+21;
            case "EAST-false-true-false" -> id+22;
            case "EAST-false-false-true" -> id+23;
            case "EAST-false-false-false" -> id+24;
            default -> id;
        };
    }
}