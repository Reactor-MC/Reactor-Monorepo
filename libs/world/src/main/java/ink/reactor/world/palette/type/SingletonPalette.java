package ink.reactor.world.palette.type;

import ink.reactor.world.palette.Palette;

public record SingletonPalette(int state) implements Palette {
    @Override
    public int size() {
        return 1;
    }

    @Override
    public int stateToId(int state) {
        return this.state == state ? 0 : -1;
    }

    @Override
    public int idToState(int id) {
        return id == 0 ? this.state : 0;
    }

    @Override
    public SingletonPalette copy() {
        return this;
    }
}