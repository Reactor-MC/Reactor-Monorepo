package ink.reactor.world.block.state;

public final class EndRod {
    public static int of(final char id, final BlockFacing facing) {
        final String sentence = String.valueOf(facing);
        return switch(sentence) {
            case "NORTH" -> id-4;
            case "EAST" -> id-3;
            case "SOUTH" -> id-2;
            case "WEST" -> id-1;
            case "DOWN" -> id+1;
            default -> id;
        };
    }
}