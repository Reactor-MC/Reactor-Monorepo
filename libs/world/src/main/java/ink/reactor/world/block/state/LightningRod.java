package ink.reactor.world.block.state;

public final class LightningRod {
    public static int of(final char id, final BlockFacing facing, final boolean powered, final boolean waterlogged) {
        final String sentence = facing+"-"+powered+"-"+waterlogged;
        return switch(sentence) {
            case "NORTH-true-true" -> id-19;
            case "NORTH-true-false" -> id-18;
            case "NORTH-false-true" -> id-17;
            case "NORTH-false-false" -> id-16;
            case "EAST-true-true" -> id-15;
            case "EAST-true-false" -> id-14;
            case "EAST-false-true" -> id-13;
            case "EAST-false-false" -> id-12;
            case "SOUTH-true-true" -> id-11;
            case "SOUTH-true-false" -> id-10;
            case "SOUTH-false-true" -> id-9;
            case "SOUTH-false-false" -> id-8;
            case "WEST-true-true" -> id-7;
            case "WEST-true-false" -> id-6;
            case "WEST-false-true" -> id-5;
            case "WEST-false-false" -> id-4;
            case "UP-true-true" -> id-3;
            case "UP-true-false" -> id-2;
            case "UP-false-true" -> id-1;
            case "DOWN-true-true" -> id+1;
            case "DOWN-true-false" -> id+2;
            case "DOWN-false-true" -> id+3;
            case "DOWN-false-false" -> id+4;
            default -> id;
        };
    }
}