package ink.reactor.zlib.igzip;

import java.nio.ByteBuffer;

public record IGzipResult(
    ByteBuffer data,
    int result
) {
    public static final int 
        /* Inflate Return values */
        ISAL_DECOMP_OK          = 0,  /* No errors encountered while decompressing */
        ISAL_END_INPUT          = 1,  /* End of input reached */
        ISAL_OUT_OVERFLOW       = 2,  /* End of output reached */
        ISAL_NAME_OVERFLOW      = 3,  /* End of gzip name buffer reached */
        ISAL_COMMENT_OVERFLOW   = 4,  /* End of gzip name buffer reached */
        ISAL_EXTRA_OVERFLOW     = 5,  /* End of extra buffer reached */
        ISAL_NEED_DICT          = 6,  /* Stream needs a dictionary to continue */
        ISAL_INVALID_BLOCK      = -1, /* Invalid deflate block found */
        ISAL_INVALID_SYMBOL     = -2, /* Invalid deflate symbol found */
        ISAL_INVALID_LOOKBACK   = -3, /* Invalid lookback distance found */
        ISAL_INVALID_WRAPPER    = -4, /* Invalid gzip/zlib wrapper found */
        ISAL_UNSUPPORTED_METHOD = -5, /* Gzip/zlib wrapper specifies unsupported compress method */
        ISAL_INCORRECT_CHECKSUM = -6, /* Incorrect checksum found */

        /* Compression Return values */
        COMP_OK                = 0,
        INVALID_FLUSH          = -7,
        INVALID_PARAM          = -8,
        STATELESS_OVERFLOW     = -1,
        ISAL_INVALID_OPERATION = -9,
        ISAL_INVALID_STATE     = -3,
        ISAL_INVALID_LEVEL     = -4, /* Invalid Compression level set */
        ISAL_INVALID_LEVEL_BUF = -5; /* Invalid buffer specified for the compression level */
}