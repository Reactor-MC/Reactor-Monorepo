package ink.reactor.world.block.state;

public final class PinkPetals {
    public static int of(final char id, final BlockFacing facing, final long flowerAmount) {
        final String sentence = facing+"-"+flowerAmount;
        return switch(sentence) {
            case "NORTH-2" -> id+1;
            case "NORTH-3" -> id+2;
            case "NORTH-4" -> id+3;
            case "SOUTH-1" -> id+4;
            case "SOUTH-2" -> id+5;
            case "SOUTH-3" -> id+6;
            case "SOUTH-4" -> id+7;
            case "WEST-1" -> id+8;
            case "WEST-2" -> id+9;
            case "WEST-3" -> id+10;
            case "WEST-4" -> id+11;
            case "EAST-1" -> id+12;
            case "EAST-2" -> id+13;
            case "EAST-3" -> id+14;
            case "EAST-4" -> id+15;
            default -> id;
        };
    }
}