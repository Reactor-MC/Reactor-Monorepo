package ink.reactor.world.block.state;

public final class WallHangingSign {
    public static int of(final char id, final BlockFacing facing, final boolean waterlogged) {
        final String sentence = facing+"-"+waterlogged;
        return switch(sentence) {
            case "NORTH-true" -> id-1;
            case "SOUTH-true" -> id+1;
            case "SOUTH-false" -> id+2;
            case "WEST-true" -> id+3;
            case "WEST-false" -> id+4;
            case "EAST-true" -> id+5;
            case "EAST-false" -> id+6;
            default -> id;
        };
    }
}