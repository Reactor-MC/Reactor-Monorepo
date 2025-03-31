package ink.reactor.world.block.state;

public final class BigDripleaf {
    public static int of(final char id, final BlockFacing facing, final BlockTilt tilt, final boolean waterlogged) {
        final String sentence = facing+"-"+tilt+"-"+waterlogged;
        return switch(sentence) {
            case "NORTH-NONE-true" -> id-1;
            case "NORTH-UNSTABLE-true" -> id+1;
            case "NORTH-UNSTABLE-false" -> id+2;
            case "NORTH-PARTIAL-true" -> id+3;
            case "NORTH-PARTIAL-false" -> id+4;
            case "NORTH-FULL-true" -> id+5;
            case "NORTH-FULL-false" -> id+6;
            case "SOUTH-NONE-true" -> id+7;
            case "SOUTH-NONE-false" -> id+8;
            case "SOUTH-UNSTABLE-true" -> id+9;
            case "SOUTH-UNSTABLE-false" -> id+10;
            case "SOUTH-PARTIAL-true" -> id+11;
            case "SOUTH-PARTIAL-false" -> id+12;
            case "SOUTH-FULL-true" -> id+13;
            case "SOUTH-FULL-false" -> id+14;
            case "WEST-NONE-true" -> id+15;
            case "WEST-NONE-false" -> id+16;
            case "WEST-UNSTABLE-true" -> id+17;
            case "WEST-UNSTABLE-false" -> id+18;
            case "WEST-PARTIAL-true" -> id+19;
            case "WEST-PARTIAL-false" -> id+20;
            case "WEST-FULL-true" -> id+21;
            case "WEST-FULL-false" -> id+22;
            case "EAST-NONE-true" -> id+23;
            case "EAST-NONE-false" -> id+24;
            case "EAST-UNSTABLE-true" -> id+25;
            case "EAST-UNSTABLE-false" -> id+26;
            case "EAST-PARTIAL-true" -> id+27;
            case "EAST-PARTIAL-false" -> id+28;
            case "EAST-FULL-true" -> id+29;
            case "EAST-FULL-false" -> id+30;
            default -> id;
        };
    }
}