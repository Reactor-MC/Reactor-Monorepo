package ink.reactor.world.block.state;

public final class Rail {
    public static int of(final char id, final BlockShape shape, final boolean waterlogged) {
        final String sentence = shape+"-"+waterlogged;
        return switch(sentence) {
            case "NORTH_SOUTH-true" -> id-1;
            case "EAST_WEST-true" -> id+1;
            case "EAST_WEST-false" -> id+2;
            case "ASCENDING_EAST-true" -> id+3;
            case "ASCENDING_EAST-false" -> id+4;
            case "ASCENDING_WEST-true" -> id+5;
            case "ASCENDING_WEST-false" -> id+6;
            case "ASCENDING_NORTH-true" -> id+7;
            case "ASCENDING_NORTH-false" -> id+8;
            case "ASCENDING_SOUTH-true" -> id+9;
            case "ASCENDING_SOUTH-false" -> id+10;
            case "SOUTH_EAST-true" -> id+11;
            case "SOUTH_EAST-false" -> id+12;
            case "SOUTH_WEST-true" -> id+13;
            case "SOUTH_WEST-false" -> id+14;
            case "NORTH_WEST-true" -> id+15;
            case "NORTH_WEST-false" -> id+16;
            case "NORTH_EAST-true" -> id+17;
            case "NORTH_EAST-false" -> id+18;
            default -> id;
        };
    }
}