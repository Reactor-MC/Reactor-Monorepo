package ink.reactor.world.block.state;

public final class Hopper {
    public static int of(final char id, final boolean enabled, final BlockFacing facing) {
        final String sentence = enabled+"-"+facing;
        return switch(sentence) {
            case "true-NORTH" -> id+1;
            case "true-SOUTH" -> id+2;
            case "true-WEST" -> id+3;
            case "true-EAST" -> id+4;
            case "false-DOWN" -> id+5;
            case "false-NORTH" -> id+6;
            case "false-SOUTH" -> id+7;
            case "false-WEST" -> id+8;
            case "false-EAST" -> id+9;
            default -> id;
        };
    }
}