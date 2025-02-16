package ink.reactor.nbt.compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class CompressionUtils {

    public static CompressionType detectCompression(final File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            final byte[] header = new byte[2];
            if (fis.read(header) < 2) {
                return null;
            }

            final int b1 = header[0] & 0xFF;
            final int b2 = header[1] & 0xFF;

            if ((b1 | (b2 << 8) & 0xff00) == GZIPInputStream.GZIP_MAGIC) {
                return CompressionType.GZIP;
            }

            /*
             * zlib magic headers
             * 78 01 - No Compression/low
             * 78 5E - Fast Compression
             * 78 9C - Default Compression
             * 78 DA - Best Compression 
             */
            if (b1 == 0x78 && (b2 == 0x01 || b2 == 0x5E || b2 == 0x9C || b2 == 0xDA)) {
                return CompressionType.ZLIB;
            }

            return CompressionType.NONE;
        }
    }

    public static byte[] getUncompressedBytes(final File file) throws IOException {
        final CompressionType compression = detectCompression(file);

        if (compression == null) {
            return null;
        }

        if (compression == CompressionType.GZIP) {
            try (GZIPInputStream is = new GZIPInputStream(new FileInputStream(file))) {
                return is.readAllBytes();
            }
        }

        if (compression == CompressionType.ZLIB) {
            try (InflaterInputStream is = new InflaterInputStream(new FileInputStream(file), new Inflater())) {
                return is.readAllBytes();
            }
        }

        return Files.readAllBytes(file.toPath());
    }
}
