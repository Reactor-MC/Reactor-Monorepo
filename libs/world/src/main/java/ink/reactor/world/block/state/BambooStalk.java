package ink.reactor.world.block.state;

public final class BambooStalk {
    public static int of(final char id, final long age, final BlockLeaves leaves, final long stage) {
        final String sentence = age+"-"+leaves+"-"+stage;
        return switch(sentence) {
            case "0-NONE-1" -> id+1;
            case "0-SMALL-0" -> id+2;
            case "0-SMALL-1" -> id+3;
            case "0-LARGE-0" -> id+4;
            case "0-LARGE-1" -> id+5;
            case "1-NONE-0" -> id+6;
            case "1-NONE-1" -> id+7;
            case "1-SMALL-0" -> id+8;
            case "1-SMALL-1" -> id+9;
            case "1-LARGE-0" -> id+10;
            case "1-LARGE-1" -> id+11;
            default -> id;
        };
    }
}