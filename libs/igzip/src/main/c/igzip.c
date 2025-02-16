#include <isa-l/igzip_lib.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>

struct isal_zstream* create_deflate(int32_t level) {
    struct isal_zstream *stream = malloc(sizeof(struct isal_zstream));
    if (stream == NULL) {
        return NULL;
    }

    isal_deflate_stateless_init(stream);

    if (level == 3) {
        stream->level_buf_size = ISAL_DEF_LVL3_DEFAULT;
    } else if (level == 2) {
        stream->level_buf_size = ISAL_DEF_LVL2_DEFAULT;
    } else {
        stream->level_buf_size = ISAL_DEF_LVL1_DEFAULT;
        level = 1;
    }

    stream->level_buf = malloc(stream->level_buf_size);
    if (stream->level_buf == NULL) {
        free(stream);
        return NULL;
    }

    stream->level = level;
    stream->gzip_flag = IGZIP_ZLIB;

    return stream;
}

struct inflate_state* create_inflate() {
    int stateSize = sizeof(struct inflate_state);
    struct inflate_state *state = malloc(stateSize);

    if (state == NULL) {
        return NULL;
    }

    isal_inflate_stateless(state);

    state->crc_flag = ISAL_ZLIB;

    return state;
}

int32_t compress(struct isal_zstream *stream, int8_t *input_array, uint8_t *output_array, const int32_t input_array_size, const int32_t output_array_size) {

    stream->next_in = input_array;
    stream->avail_in = input_array_size;

    stream->next_out = output_array;
    stream->avail_out = output_array_size;

    const int32_t result = isal_deflate_stateless(stream);
    const int32_t bytesCompressed = stream->total_out;

    isal_deflate_reset(stream);
    stream->next_in = NULL;
    stream->next_out = NULL;

    if (result == COMP_OK) {
        return bytesCompressed;
    };
    return result;
}

int64_t decompress(struct inflate_state *state, int8_t *input_array, int8_t *output_array, const int32_t input_array_size, const int32_t output_array_size) {
    state->avail_in = input_array_size;
    state->next_in = input_array;
    state->block_state = ISAL_BLOCK_NEW_HDR;

    state->avail_out = output_array_size;
    state->next_out = output_array;

    const int32_t result = isal_inflate_stateless(state);
    if (result == ISAL_DECOMP_OK) {
        return ((int64_t)(output_array_size - state->avail_out) << 32) | (result);
    }
    return result;
}

void free_deflate(struct isal_zstream *stream) {
    if (stream != NULL) {
        free(stream->level_buf);
        free(stream);
    }
}

void free_inflate(struct inflate_state *state) {
    if (state != NULL) {
        free(state);
    }
}