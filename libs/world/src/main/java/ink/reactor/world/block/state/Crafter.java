package ink.reactor.world.block.state;

public final class Crafter {
    public static int of(final char id, final boolean crafting, final BlockOrientation orientation, final boolean triggered) {
        final String sentence = crafting+"-"+orientation+"-"+triggered;
        return switch(sentence) {
            case "true-DOWN_EAST-true" -> id-45;
            case "true-DOWN_EAST-false" -> id-44;
            case "true-DOWN_NORTH-true" -> id-43;
            case "true-DOWN_NORTH-false" -> id-42;
            case "true-DOWN_SOUTH-true" -> id-41;
            case "true-DOWN_SOUTH-false" -> id-40;
            case "true-DOWN_WEST-true" -> id-39;
            case "true-DOWN_WEST-false" -> id-38;
            case "true-UP_EAST-true" -> id-37;
            case "true-UP_EAST-false" -> id-36;
            case "true-UP_NORTH-true" -> id-35;
            case "true-UP_NORTH-false" -> id-34;
            case "true-UP_SOUTH-true" -> id-33;
            case "true-UP_SOUTH-false" -> id-32;
            case "true-UP_WEST-true" -> id-31;
            case "true-UP_WEST-false" -> id-30;
            case "true-WEST_UP-true" -> id-29;
            case "true-WEST_UP-false" -> id-28;
            case "true-EAST_UP-true" -> id-27;
            case "true-EAST_UP-false" -> id-26;
            case "true-NORTH_UP-true" -> id-25;
            case "true-NORTH_UP-false" -> id-24;
            case "true-SOUTH_UP-true" -> id-23;
            case "true-SOUTH_UP-false" -> id-22;
            case "false-DOWN_EAST-true" -> id-21;
            case "false-DOWN_EAST-false" -> id-20;
            case "false-DOWN_NORTH-true" -> id-19;
            case "false-DOWN_NORTH-false" -> id-18;
            case "false-DOWN_SOUTH-true" -> id-17;
            case "false-DOWN_SOUTH-false" -> id-16;
            case "false-DOWN_WEST-true" -> id-15;
            case "false-DOWN_WEST-false" -> id-14;
            case "false-UP_EAST-true" -> id-13;
            case "false-UP_EAST-false" -> id-12;
            case "false-UP_NORTH-true" -> id-11;
            case "false-UP_NORTH-false" -> id-10;
            case "false-UP_SOUTH-true" -> id-9;
            case "false-UP_SOUTH-false" -> id-8;
            case "false-UP_WEST-true" -> id-7;
            case "false-UP_WEST-false" -> id-6;
            case "false-WEST_UP-true" -> id-5;
            case "false-WEST_UP-false" -> id-4;
            case "false-EAST_UP-true" -> id-3;
            case "false-EAST_UP-false" -> id-2;
            case "false-NORTH_UP-true" -> id-1;
            case "false-SOUTH_UP-true" -> id+1;
            case "false-SOUTH_UP-false" -> id+2;
            default -> id;
        };
    }
}