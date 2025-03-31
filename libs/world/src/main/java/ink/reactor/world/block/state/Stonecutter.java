package ink.reactor.world.block.state;

public final class Stonecutter {
    public static int of(final char id, final BlockFacing facing) {
        final String sentence = String.valueOf(facing);
        return switch(sentence) {
            case "SOUTH" -> id+1;
            case "WEST" -> id+2;
            case "EAST" -> id+3;
            default -> id;
        };
    }
}