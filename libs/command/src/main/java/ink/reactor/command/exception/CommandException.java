package ink.reactor.command.exception;

public final class CommandException extends RuntimeException {
    public CommandException(String message, Throwable error) {
        super(message, error);
    }
    public CommandException(String message) {
        super(message);
    }
}
