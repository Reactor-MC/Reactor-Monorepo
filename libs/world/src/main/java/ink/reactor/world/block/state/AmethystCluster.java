package ink.reactor.world.block.state;

public final class AmethystCluster {
    public static int of(final char id, final BlockFacing facing, final boolean waterlogged) {
        final String sentence = facing+"-"+waterlogged;
        return switch(sentence) {
            case "NORTH-true" -> id-9;
            case "NORTH-false" -> id-8;
            case "EAST-true" -> id-7;
            case "EAST-false" -> id-6;
            case "SOUTH-true" -> id-5;
            case "SOUTH-false" -> id-4;
            case "WEST-true" -> id-3;
            case "WEST-false" -> id-2;
            case "UP-true" -> id-1;
            case "DOWN-true" -> id+1;
            case "DOWN-false" -> id+2;
            default -> id;
        };
    }
}