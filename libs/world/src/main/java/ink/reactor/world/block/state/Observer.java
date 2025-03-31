package ink.reactor.world.block.state;

public final class Observer {
    public static int of(final char id, final BlockFacing facing, final boolean powered) {
        final String sentence = facing+"-"+powered;
        return switch(sentence) {
            case "NORTH-true" -> id-5;
            case "NORTH-false" -> id-4;
            case "EAST-true" -> id-3;
            case "EAST-false" -> id-2;
            case "SOUTH-true" -> id-1;
            case "WEST-true" -> id+1;
            case "WEST-false" -> id+2;
            case "UP-true" -> id+3;
            case "UP-false" -> id+4;
            case "DOWN-true" -> id+5;
            case "DOWN-false" -> id+6;
            default -> id;
        };
    }
}