package ink.reactor.dataparser.type.block;

public final class Button {

    public static int of(final char id, final BlockFace face, final BlockFacing facing, final boolean powered) {
        final String sentence = String.valueOf(face)+'-'+facing+'-'+powered;
        return switch(sentence) {
            case "floor-north-true" -> id-9;
            case "floor-north-false" -> id-8;
            case "floor-south-true" -> id-7;
            case "floor-south-false" -> id-6;
            case "floor-west-true" -> id-5;
            case "floor-west-false" -> id-4;
            case "floor-east-true" -> id-3;
            case "floor-east-false" -> id-2;
            case "wall-north-true" -> id-1;
            case "wall-north-false" -> id;
            case "wall-south-true" -> id+1;
            case "wall-south-false" -> id+2;
            case "wall-west-true" -> id+3;
            case "wall-west-false" -> id+4;
            case "wall-east-true" -> id+5;
            case "wall-east-false" -> id+6;
            case "ceiling-north-true" -> id+7;
            case "ceiling-north-false" -> id+8;
            case "ceiling-south-true" -> id+9;
            case "ceiling-south-false" -> id+10;
            case "ceiling-west-true" -> id+11;
            case "ceiling-west-false" -> id+12;
            case "ceiling-east-true" -> id+13;
            case "ceiling-east-false" -> id+14;
            default -> -1;
        };
    }
}