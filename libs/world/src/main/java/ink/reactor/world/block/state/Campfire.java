package ink.reactor.world.block.state;

public final class Campfire {
    public static int of(final char id, final BlockFacing facing, final boolean lit, final boolean signalFire, final boolean waterlogged) {
        final String sentence = facing+"-"+lit+"-"+signalFire+"-"+waterlogged;
        return switch(sentence) {
            case "NORTH-true-true-true" -> id-3;
            case "NORTH-true-true-false" -> id-2;
            case "NORTH-true-false-true" -> id-1;
            case "NORTH-false-true-true" -> id+1;
            case "NORTH-false-true-false" -> id+2;
            case "NORTH-false-false-true" -> id+3;
            case "NORTH-false-false-false" -> id+4;
            case "SOUTH-true-true-true" -> id+5;
            case "SOUTH-true-true-false" -> id+6;
            case "SOUTH-true-false-true" -> id+7;
            case "SOUTH-true-false-false" -> id+8;
            case "SOUTH-false-true-true" -> id+9;
            case "SOUTH-false-true-false" -> id+10;
            case "SOUTH-false-false-true" -> id+11;
            case "SOUTH-false-false-false" -> id+12;
            case "WEST-true-true-true" -> id+13;
            case "WEST-true-true-false" -> id+14;
            case "WEST-true-false-true" -> id+15;
            case "WEST-true-false-false" -> id+16;
            case "WEST-false-true-true" -> id+17;
            case "WEST-false-true-false" -> id+18;
            case "WEST-false-false-true" -> id+19;
            case "WEST-false-false-false" -> id+20;
            case "EAST-true-true-true" -> id+21;
            case "EAST-true-true-false" -> id+22;
            case "EAST-true-false-true" -> id+23;
            case "EAST-true-false-false" -> id+24;
            case "EAST-false-true-true" -> id+25;
            case "EAST-false-true-false" -> id+26;
            case "EAST-false-false-true" -> id+27;
            case "EAST-false-false-false" -> id+28;
            default -> id;
        };
    }
}