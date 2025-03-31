package ink.reactor.world.block.state;

public final class PoweredRail {
    public static int of(final char id, final boolean powered, final BlockShape shape, final boolean waterlogged) {
        final String sentence = powered+"-"+shape+"-"+waterlogged;
        return switch(sentence) {
            case "true-NORTH_SOUTH-true" -> id-13;
            case "true-NORTH_SOUTH-false" -> id-12;
            case "true-EAST_WEST-true" -> id-11;
            case "true-EAST_WEST-false" -> id-10;
            case "true-ASCENDING_EAST-true" -> id-9;
            case "true-ASCENDING_EAST-false" -> id-8;
            case "true-ASCENDING_WEST-true" -> id-7;
            case "true-ASCENDING_WEST-false" -> id-6;
            case "true-ASCENDING_NORTH-true" -> id-5;
            case "true-ASCENDING_NORTH-false" -> id-4;
            case "true-ASCENDING_SOUTH-true" -> id-3;
            case "true-ASCENDING_SOUTH-false" -> id-2;
            case "false-NORTH_SOUTH-true" -> id-1;
            case "false-EAST_WEST-true" -> id+1;
            case "false-EAST_WEST-false" -> id+2;
            case "false-ASCENDING_EAST-true" -> id+3;
            case "false-ASCENDING_EAST-false" -> id+4;
            case "false-ASCENDING_WEST-true" -> id+5;
            case "false-ASCENDING_WEST-false" -> id+6;
            case "false-ASCENDING_NORTH-true" -> id+7;
            case "false-ASCENDING_NORTH-false" -> id+8;
            case "false-ASCENDING_SOUTH-true" -> id+9;
            case "false-ASCENDING_SOUTH-false" -> id+10;
            default -> id;
        };
    }
}