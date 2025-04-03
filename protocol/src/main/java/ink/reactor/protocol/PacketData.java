package ink.reactor.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class PacketData {
    private byte[] buffer;
}
