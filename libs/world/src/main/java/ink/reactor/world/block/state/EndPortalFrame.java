package ink.reactor.world.block.state;

public final class EndPortalFrame {
    public static int of(final char id, final boolean eye, final BlockFacing facing) {
        final String sentence = eye+"-"+facing;
        return switch(sentence) {
            case "true-NORTH" -> id-4;
            case "true-SOUTH" -> id-3;
            case "true-WEST" -> id-2;
            case "true-EAST" -> id-1;
            case "false-SOUTH" -> id+1;
            case "false-WEST" -> id+2;
            case "false-EAST" -> id+3;
            default -> id;
        };
    }
}