package ink.reactor.world.block.state;

public final class MovingPiston {
    public static int of(final char id, final BlockType type, final BlockFacing facing) {
        final String sentence = type+"-"+facing;
        return switch(sentence) {
            case "STICKY-NORTH" -> id+1;
            case "NORMAL-EAST" -> id+2;
            case "STICKY-EAST" -> id+3;
            case "NORMAL-SOUTH" -> id+4;
            case "STICKY-SOUTH" -> id+5;
            case "NORMAL-WEST" -> id+6;
            case "STICKY-WEST" -> id+7;
            case "NORMAL-UP" -> id+8;
            case "STICKY-UP" -> id+9;
            case "NORMAL-DOWN" -> id+10;
            case "STICKY-DOWN" -> id+11;
            default -> id;
        };
    }
}