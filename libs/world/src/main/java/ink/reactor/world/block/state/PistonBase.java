package ink.reactor.world.block.state;

public final class PistonBase {
    public static int of(final char id, final boolean extended, final BlockFacing facing) {
        final String sentence = extended+"-"+facing;
        return switch(sentence) {
            case "true-NORTH" -> id-6;
            case "true-EAST" -> id-5;
            case "true-SOUTH" -> id-4;
            case "true-WEST" -> id-3;
            case "true-UP" -> id-2;
            case "true-DOWN" -> id-1;
            case "false-EAST" -> id+1;
            case "false-SOUTH" -> id+2;
            case "false-WEST" -> id+3;
            case "false-UP" -> id+4;
            case "false-DOWN" -> id+5;
            default -> id;
        };
    }
}