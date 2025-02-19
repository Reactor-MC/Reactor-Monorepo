package ink.reactor.zlib.isal;

import java.nio.ByteBuffer;

public interface IGzipConsumer {
    void accept(ByteBuffer data, int resultCode);
}
