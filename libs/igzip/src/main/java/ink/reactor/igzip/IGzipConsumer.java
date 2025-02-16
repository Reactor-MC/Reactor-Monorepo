package ink.reactor.igzip;

import java.nio.ByteBuffer;

public interface IGzipConsumer {
    void accept(ByteBuffer data, int resultCode);
}
