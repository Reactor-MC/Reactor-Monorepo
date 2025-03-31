package ink.reactor.world.block.state;

public final class PointedDripstone {
    public static int of(final char id, final BlockThickness thickness, final BlockVerticalDirection verticalDirection, final boolean waterlogged) {
        final String sentence = thickness+"-"+verticalDirection+"-"+waterlogged;
        return switch(sentence) {
            case "TIP_MERGE-UP-true" -> id-5;
            case "TIP_MERGE-UP-false" -> id-4;
            case "TIP_MERGE-DOWN-true" -> id-3;
            case "TIP_MERGE-DOWN-false" -> id-2;
            case "TIP-UP-true" -> id-1;
            case "TIP-DOWN-true" -> id+1;
            case "TIP-DOWN-false" -> id+2;
            case "FRUSTUM-UP-true" -> id+3;
            case "FRUSTUM-UP-false" -> id+4;
            case "FRUSTUM-DOWN-true" -> id+5;
            case "FRUSTUM-DOWN-false" -> id+6;
            case "MIDDLE-UP-true" -> id+7;
            case "MIDDLE-UP-false" -> id+8;
            case "MIDDLE-DOWN-true" -> id+9;
            case "MIDDLE-DOWN-false" -> id+10;
            case "BASE-UP-true" -> id+11;
            case "BASE-UP-false" -> id+12;
            case "BASE-DOWN-true" -> id+13;
            case "BASE-DOWN-false" -> id+14;
            default -> id;
        };
    }
}