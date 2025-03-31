package ink.reactor.world.block.state;

public final class Beehive {
    public static int of(final char id, final BlockFacing facing, final long honeyLevel) {
        final String sentence = facing+"-"+honeyLevel;
        return switch(sentence) {
            case "NORTH-1" -> id+1;
            case "NORTH-2" -> id+2;
            case "NORTH-3" -> id+3;
            case "NORTH-4" -> id+4;
            case "NORTH-5" -> id+5;
            case "SOUTH-0" -> id+6;
            case "SOUTH-1" -> id+7;
            case "SOUTH-2" -> id+8;
            case "SOUTH-3" -> id+9;
            case "SOUTH-4" -> id+10;
            case "SOUTH-5" -> id+11;
            case "WEST-0" -> id+12;
            case "WEST-1" -> id+13;
            case "WEST-2" -> id+14;
            case "WEST-3" -> id+15;
            case "WEST-4" -> id+16;
            case "WEST-5" -> id+17;
            case "EAST-0" -> id+18;
            case "EAST-1" -> id+19;
            case "EAST-2" -> id+20;
            case "EAST-3" -> id+21;
            case "EAST-4" -> id+22;
            case "EAST-5" -> id+23;
            default -> id;
        };
    }
}