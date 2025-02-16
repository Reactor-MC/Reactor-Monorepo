package ink.reactor.chat.interactivity;

public record ClickEvent(
    Action action,
    String value
) {
    public enum Action {
        OPEN_URL,
        OPEN_FILE,
        RUN_COMMAND,
        SUGGEST_COMMAND,
        CHANGE_PAGE,
        COPY_TO_CLIPBOARD;

        private final byte[] id = name().toLowerCase().getBytes();
    
        public byte[] getId() {
            return id;
        }
    }

    public static ClickEvent openUrl(final String url) {
        return new ClickEvent(Action.OPEN_URL, url);
    }

    public static ClickEvent openFile(final String filePath) {
        return new ClickEvent(Action.OPEN_FILE, filePath);
    }

    public static ClickEvent runCommand(final String command) {
        return new ClickEvent(Action.RUN_COMMAND, command);
    }

    public static ClickEvent suggestCommand(final String command) {
        return new ClickEvent(Action.SUGGEST_COMMAND, command);
    }

    public static ClickEvent changePage(final String page) {
        return new ClickEvent(Action.CHANGE_PAGE, page);
    }

    public static ClickEvent copyToClipboard(final String text) {
        return new ClickEvent(Action.COPY_TO_CLIPBOARD, text);
    }
}