package ink.reactor.world.block;

public final class BlockUtils {

    public static boolean isMotionSolid(final char id) {
        return
            id != Block.LAVA.id && id != Block.WATER.id &&
            id != Block.BAMBOO_SAPLING.id && id != Block.CACTUS.id;
    }
}