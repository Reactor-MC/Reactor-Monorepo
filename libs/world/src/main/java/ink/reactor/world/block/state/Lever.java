package ink.reactor.world.block.state;

public final class Lever {
    public static int of(final char id, final BlockFace face, final BlockFacing facing, final boolean powered) {
        final String sentence = face+"-"+facing+"-"+powered;
        return switch(sentence) {
            case "FLOOR-NORTH-true" -> id-9;
            case "FLOOR-NORTH-false" -> id-8;
            case "FLOOR-SOUTH-true" -> id-7;
            case "FLOOR-SOUTH-false" -> id-6;
            case "FLOOR-WEST-true" -> id-5;
            case "FLOOR-WEST-false" -> id-4;
            case "FLOOR-EAST-true" -> id-3;
            case "FLOOR-EAST-false" -> id-2;
            case "WALL-NORTH-true" -> id-1;
            case "WALL-SOUTH-true" -> id+1;
            case "WALL-SOUTH-false" -> id+2;
            case "WALL-WEST-true" -> id+3;
            case "WALL-WEST-false" -> id+4;
            case "WALL-EAST-true" -> id+5;
            case "WALL-EAST-false" -> id+6;
            case "CEILING-NORTH-true" -> id+7;
            case "CEILING-NORTH-false" -> id+8;
            case "CEILING-SOUTH-true" -> id+9;
            case "CEILING-SOUTH-false" -> id+10;
            case "CEILING-WEST-true" -> id+11;
            case "CEILING-WEST-false" -> id+12;
            case "CEILING-EAST-true" -> id+13;
            case "CEILING-EAST-false" -> id+14;
            default -> id;
        };
    }
}