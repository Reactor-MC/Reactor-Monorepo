package ink.reactor.command.argument;

public record ArgumentType(int index, byte id) {
    public static final byte
        ID_BOOLEAN = 0,
        ID_FLOAT = 1,
        ID_DOUBLE = 2,
        ID_INT = 3,
        ID_LONG = 4,
        ID_STRING = 5;
}