package ink.reactor.util;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public final class UUIDCreator {

    private UUIDCreator() {
        throw new IllegalAccessError("Util class can't be instance");
    }

    public static UUID stringToUUID(String input) {
        final byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        final byte[] uuidBytes = new byte[16];

        final int minLength = Math.min(input.length(), 16);
        for (int i = 0; i < minLength; i++) {
            uuidBytes[i] = bytes[i];
        }

        long mostSigBits = 0;
        for (int i = 0; i < 8; i++) {
            mostSigBits = (mostSigBits << 8) | (uuidBytes[i] & 0xFF);
        }

        long leastSigBits = 0;
        for (int i = 8; i < 16; i++) {
            leastSigBits = (leastSigBits << 8) | (uuidBytes[i] & 0xFF);
        }
        return new UUID(mostSigBits, leastSigBits);
    }
}