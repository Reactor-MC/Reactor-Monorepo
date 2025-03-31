package ink.reactor.world.block.state;

public final class BaseCoralWallFan {
    public static int of(final char id, final BlockFacing facing, final boolean waterlogged) {
        final String sentence = facing+"-"+waterlogged;
        return switch(sentence) {
            case "NORTH-false" -> id+1;
            case "SOUTH-true" -> id+2;
            case "SOUTH-false" -> id+3;
            case "WEST-true" -> id+4;
            case "WEST-false" -> id+5;
            case "EAST-true" -> id+6;
            case "EAST-false" -> id+7;
            default -> id;
        };
    }
}