package ink.reactor.world.block.state;

public final class Cocoa {
    public static int of(final char id, final long age, final BlockFacing facing) {
        final String sentence = age+"-"+facing;
        return switch(sentence) {
            case "0-SOUTH" -> id+1;
            case "0-WEST" -> id+2;
            case "0-EAST" -> id+3;
            case "1-NORTH" -> id+4;
            case "1-SOUTH" -> id+5;
            case "1-WEST" -> id+6;
            case "1-EAST" -> id+7;
            case "2-NORTH" -> id+8;
            case "2-SOUTH" -> id+9;
            case "2-WEST" -> id+10;
            case "2-EAST" -> id+11;
            default -> id;
        };
    }
}