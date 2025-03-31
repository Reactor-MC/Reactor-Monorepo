package ink.reactor.world.block.state;

public final class Barrel {
    public static int of(final char id, final BlockFacing facing, final boolean open) {
        final String sentence = facing+"-"+open;
        return switch(sentence) {
            case "NORTH-true" -> id-1;
            case "EAST-true" -> id+1;
            case "EAST-false" -> id+2;
            case "SOUTH-true" -> id+3;
            case "SOUTH-false" -> id+4;
            case "WEST-true" -> id+5;
            case "WEST-false" -> id+6;
            case "UP-true" -> id+7;
            case "UP-false" -> id+8;
            case "DOWN-true" -> id+9;
            case "DOWN-false" -> id+10;
            default -> id;
        };
    }
}