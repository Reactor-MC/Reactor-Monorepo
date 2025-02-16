package ink.reactor.chat;

public enum ChatMode {
    ENABLED,
    COMMAND_ONLY,
    HIDDEN;

    public static ChatMode byId(final int id) {
        if (id == 1) {
            return COMMAND_ONLY;
        }
        if (id == 2) {
            return HIDDEN;
        }
        return ENABLED;
    }
}