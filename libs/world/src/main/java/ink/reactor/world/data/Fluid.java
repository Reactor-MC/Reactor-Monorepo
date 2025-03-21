package ink.reactor.world.data;

import lombok.Getter;

@Getter
public final class Fluid {

    public static final Fluid[] ALL = new Fluid[5];

    public static final Fluid
        EMPTY = new Fluid(0, "empty"),
        FLOWING_WATER = new Fluid(1, "flowing_water"),
        WATER = new Fluid(2, "water"),
        FLOWING_LAVA = new Fluid(3, "flowing_lava"),
        LAVA = new Fluid(4, "lava");

    private final String identifier;
    private final int id;
    
    public Fluid(String identifier, int id) {
        this.identifier = identifier;
        this.id = id;
    }

    private Fluid(int id, String identifier) {
        this(identifier, id);
        ALL[id] = this;
    }
}