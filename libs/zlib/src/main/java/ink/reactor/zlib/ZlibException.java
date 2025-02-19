package ink.reactor.zlib;

public final class ZlibException extends Exception {

    public ZlibException(String message) {
        super(message);
    }

    public ZlibException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
