package ink.reactor.world.block.state;

public final class Jigsaw {
    public static int of(final char id, final BlockOrientation orientation) {
        final String sentence = String.valueOf(orientation);
        return switch(sentence) {
            case "DOWN_EAST" -> id-10;
            case "DOWN_NORTH" -> id-9;
            case "DOWN_SOUTH" -> id-8;
            case "DOWN_WEST" -> id-7;
            case "UP_EAST" -> id-6;
            case "UP_NORTH" -> id-5;
            case "UP_SOUTH" -> id-4;
            case "UP_WEST" -> id-3;
            case "WEST_UP" -> id-2;
            case "EAST_UP" -> id-1;
            case "SOUTH_UP" -> id+1;
            default -> id;
        };
    }
}