package ink.reactor.world.block.state;

public final class TorchflowerCrop {
    public static int of(final char id, final long age) {
        final String sentence = String.valueOf(age);
        return switch(sentence) {
            case "1" -> id+1;
            default -> id;
        };
    }
}