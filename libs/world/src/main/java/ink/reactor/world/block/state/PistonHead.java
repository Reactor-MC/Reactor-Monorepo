package ink.reactor.world.block.state;

public final class PistonHead {
    public static int of(final char id, final BlockType type, final BlockFacing facing, final boolean shortNumber) {
        final String sentence = type+"-"+facing+"-"+shortNumber;
        return switch(sentence) {
            case "NORMAL-NORTH-true" -> id-2;
            case "STICKY-NORTH-true" -> id-1;
            case "STICKY-NORTH-false" -> id+1;
            case "NORMAL-EAST-true" -> id+2;
            case "STICKY-EAST-true" -> id+3;
            case "NORMAL-EAST-false" -> id+4;
            case "STICKY-EAST-false" -> id+5;
            case "NORMAL-SOUTH-true" -> id+6;
            case "STICKY-SOUTH-true" -> id+7;
            case "NORMAL-SOUTH-false" -> id+8;
            case "STICKY-SOUTH-false" -> id+9;
            case "NORMAL-WEST-true" -> id+10;
            case "STICKY-WEST-true" -> id+11;
            case "NORMAL-WEST-false" -> id+12;
            case "STICKY-WEST-false" -> id+13;
            case "NORMAL-UP-true" -> id+14;
            case "STICKY-UP-true" -> id+15;
            case "NORMAL-UP-false" -> id+16;
            case "STICKY-UP-false" -> id+17;
            case "NORMAL-DOWN-true" -> id+18;
            case "STICKY-DOWN-true" -> id+19;
            case "NORMAL-DOWN-false" -> id+20;
            case "STICKY-DOWN-false" -> id+21;
            default -> id;
        };
    }
}