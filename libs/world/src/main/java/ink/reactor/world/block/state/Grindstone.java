package ink.reactor.world.block.state;

public final class Grindstone {
    public static int of(final char id, final BlockFace face, final BlockFacing facing) {
        final String sentence = face+"-"+facing;
        return switch(sentence) {
            case "FLOOR-NORTH" -> id-4;
            case "FLOOR-SOUTH" -> id-3;
            case "FLOOR-WEST" -> id-2;
            case "FLOOR-EAST" -> id-1;
            case "WALL-SOUTH" -> id+1;
            case "WALL-WEST" -> id+2;
            case "WALL-EAST" -> id+3;
            case "CEILING-NORTH" -> id+4;
            case "CEILING-SOUTH" -> id+5;
            case "CEILING-WEST" -> id+6;
            case "CEILING-EAST" -> id+7;
            default -> id;
        };
    }
}