package ink.reactor.world.palette.type;

import ink.reactor.world.palette.Palette;

/**
 * A global palette that maps 1:1.
 */
public class GlobalPalette implements Palette {
    public static final GlobalPalette INSTANCE = new GlobalPalette();

    @Override
    public int size() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int stateToId(int state) {
        return state;
    }

    @Override
    public int idToState(int id) {
        return id;
    }

    @Override
    public GlobalPalette copy() {
        return this;
    }
}