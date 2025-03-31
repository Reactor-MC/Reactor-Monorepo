/**
 * .json files generated with Data generators.
 *   <a href="https://minecraft.wiki/w/Minecraft_Wiki:Projects/wiki.vg_merge/Data_Generators">Data generators</a>
 * Jsons combined using
 *   @see ink.reactor.dataparser.util.JsonCombiner
 */
package ink.reactor.world.data;
import java.util.ArrayList;
import java.util.List;

public record Biome(
    int id,
    String name,
    boolean hasPrecipitation,
    double temperature,
    String temperatureModifier,
    double downFall,
    Effects effects
) {
    public static final List<Biome> ALL = new ArrayList<>(65);

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof Biome biome && biome.id == this.id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    private static Biome vanilla(final Biome biome) {
        ALL.add(biome);
        return biome;
    }

    public record Effects(
        int fogColor,
        Integer foliageColor,
        Integer grassColor,
        String grassColorModifier,
        String ambientSound,
        int skyColor,
        int waterColor,
        int waterFogColor,
        MoodSound moodSound,
        Music music,
        AdditionSound additionSound,
        Particle particle
    ){

        public record MoodSound(
            String sound,
            int tickDelay,
            int blockSearchExtent,
            double offSet
        ){}

        public record Music(
            int maxDelay,
            int minDelay,
            boolean replaceCurrentMusic,
            String sound
        ){}

        public record AdditionSound(
            String sound,
            double tickChance
        ){}

        public record Particle(
            String type,
            double probability
        ){}
    }

    public static final Biome
        BADLANDS = vanilla(new Biome(0,"badlands",false,2.0,"none",0.0,new Effects(12638463,10387789,9470285,null,null,7254527,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        BAMBOO_JUNGLE = vanilla(new Biome(1,"bamboo_jungle",true,0.95,"none",0.9,new Effects(12638463,null,null,null,null,7842047,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        BASALT_DELTAS = vanilla(new Biome(2,"basalt_deltas",false,2.0,"none",0.0,new Effects(6840176,null,null,null,"minecraft:ambient.basalt_deltas.loop",7254527,4159204,329011,new Effects.MoodSound("minecraft:ambient.basalt_deltas.mood",6000,2,8),null,new Effects.AdditionSound("minecraft:ambient.basalt_deltas.additions",0.0111),new Effects.Particle("minecraft:white_ash",0.118093334)))),
        BEACH = vanilla(new Biome(3,"beach",true,0.8,"none",0.4,new Effects(12638463,null,null,null,null,7907327,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        BIRCH_FOREST = vanilla(new Biome(4,"birch_forest",true,0.6,"none",0.6,new Effects(12638463,null,null,null,null,8037887,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        CHERRY_GROVE = vanilla(new Biome(5,"cherry_grove",true,0.5,"none",0.8,new Effects(12638463,11983713,11983713,null,null,8103167,6141935,6141935,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        COLD_OCEAN = vanilla(new Biome(6,"cold_ocean",true,0.5,"none",0.5,new Effects(12638463,null,null,null,null,8103167,4020182,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        CRIMSON_FOREST = vanilla(new Biome(7,"crimson_forest",false,2.0,"none",0.0,new Effects(3343107,null,null,null,"minecraft:ambient.crimson_forest.loop",7254527,4159204,329011,new Effects.MoodSound("minecraft:ambient.crimson_forest.mood",6000,2,8),null,new Effects.AdditionSound("minecraft:ambient.crimson_forest.additions",0.0111),new Effects.Particle("minecraft:crimson_spore",0.025)))),
        DARK_FOREST = vanilla(new Biome(8,"dark_forest",true,0.7,"none",0.8,new Effects(12638463,null,null,"dark_forest",null,7972607,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        DEEP_COLD_OCEAN = vanilla(new Biome(9,"deep_cold_ocean",true,0.5,"none",0.5,new Effects(12638463,null,null,null,null,8103167,4020182,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        DEEP_DARK = vanilla(new Biome(10,"deep_dark",true,0.8,"none",0.4,new Effects(12638463,null,null,null,null,7907327,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        DEEP_FROZEN_OCEAN = vanilla(new Biome(11,"deep_frozen_ocean",true,0.5,"frozen",0.5,new Effects(12638463,null,null,null,null,8103167,3750089,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        DEEP_LUKEWARM_OCEAN = vanilla(new Biome(12,"deep_lukewarm_ocean",true,0.5,"none",0.5,new Effects(12638463,null,null,null,null,8103167,4566514,267827,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        DEEP_OCEAN = vanilla(new Biome(13,"deep_ocean",true,0.5,"none",0.5,new Effects(12638463,null,null,null,null,8103167,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        DESERT = vanilla(new Biome(14,"desert",false,2.0,"none",0.0,new Effects(12638463,null,null,null,null,7254527,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        DRIPSTONE_CAVES = vanilla(new Biome(15,"dripstone_caves",true,0.8,"none",0.4,new Effects(12638463,null,null,null,null,7907327,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        END_BARRENS = vanilla(new Biome(16,"end_barrens",false,0.5,"none",0.5,new Effects(10518688,null,null,null,null,0,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        END_HIGHLANDS = vanilla(new Biome(17,"end_highlands",false,0.5,"none",0.5,new Effects(10518688,null,null,null,null,0,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        END_MIDLANDS = vanilla(new Biome(18,"end_midlands",false,0.5,"none",0.5,new Effects(10518688,null,null,null,null,0,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        ERODED_BADLANDS = vanilla(new Biome(19,"eroded_badlands",false,2.0,"none",0.0,new Effects(12638463,10387789,9470285,null,null,7254527,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        FLOWER_FOREST = vanilla(new Biome(20,"flower_forest",true,0.7,"none",0.8,new Effects(12638463,null,null,null,null,7972607,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        FOREST = vanilla(new Biome(21,"forest",true,0.7,"none",0.8,new Effects(12638463,null,null,null,null,7972607,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        FROZEN_OCEAN = vanilla(new Biome(22,"frozen_ocean",true,0.0,"frozen",0.5,new Effects(12638463,null,null,null,null,8364543,3750089,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        FROZEN_PEAKS = vanilla(new Biome(23,"frozen_peaks",true,-0.7,"none",0.9,new Effects(12638463,null,null,null,null,8756735,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        FROZEN_RIVER = vanilla(new Biome(24,"frozen_river",true,0.0,"none",0.5,new Effects(12638463,null,null,null,null,8364543,3750089,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        GROVE = vanilla(new Biome(25,"grove",true,-0.2,"none",0.8,new Effects(12638463,null,null,null,null,8495359,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        ICE_SPIKES = vanilla(new Biome(26,"ice_spikes",true,0.0,"none",0.5,new Effects(12638463,null,null,null,null,8364543,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        JAGGED_PEAKS = vanilla(new Biome(27,"jagged_peaks",true,-0.7,"none",0.9,new Effects(12638463,null,null,null,null,8756735,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        JUNGLE = vanilla(new Biome(28,"jungle",true,0.95,"none",0.9,new Effects(12638463,null,null,null,null,7842047,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        LUKEWARM_OCEAN = vanilla(new Biome(29,"lukewarm_ocean",true,0.5,"none",0.5,new Effects(12638463,null,null,null,null,8103167,4566514,267827,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        LUSH_CAVES = vanilla(new Biome(30,"lush_caves",true,0.5,"none",0.5,new Effects(12638463,null,null,null,null,8103167,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        MANGROVE_SWAMP = vanilla(new Biome(31,"mangrove_swamp",true,0.8,"none",0.9,new Effects(12638463,9285927,null,"swamp",null,7907327,3832426,5077600,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        MEADOW = vanilla(new Biome(32,"meadow",true,0.5,"none",0.8,new Effects(12638463,null,null,null,null,8103167,937679,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        MUSHROOM_FIELDS = vanilla(new Biome(33,"mushroom_fields",true,0.9,"none",1.0,new Effects(12638463,null,null,null,null,7842047,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        NETHER_WASTES = vanilla(new Biome(34,"nether_wastes",false,2.0,"none",0.0,new Effects(3344392,null,null,null,"minecraft:ambient.nether_wastes.loop",7254527,4159204,329011,new Effects.MoodSound("minecraft:ambient.nether_wastes.mood",6000,2,8),null,new Effects.AdditionSound("minecraft:ambient.nether_wastes.additions",0.0111),null))),
        OCEAN = vanilla(new Biome(35,"ocean",true,0.5,"none",0.5,new Effects(12638463,null,null,null,null,8103167,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        OLD_GROWTH_BIRCH_FOREST = vanilla(new Biome(36,"old_growth_birch_forest",true,0.6,"none",0.6,new Effects(12638463,null,null,null,null,8037887,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        OLD_GROWTH_PINE_TAIGA = vanilla(new Biome(37,"old_growth_pine_taiga",true,0.3,"none",0.8,new Effects(12638463,null,null,null,null,8168447,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        OLD_GROWTH_SPRUCE_TAIGA = vanilla(new Biome(38,"old_growth_spruce_taiga",true,0.25,"none",0.8,new Effects(12638463,null,null,null,null,8233983,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        PALE_GARDEN = vanilla(new Biome(39,"pale_garden",true,0.7,"none",0.8,new Effects(8484720,8883574,7832178,null,null,12171705,7768221,5597568,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        PLAINS = vanilla(new Biome(40,"plains",true,0.8,"none",0.4,new Effects(12638463,null,null,null,null,7907327,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        RIVER = vanilla(new Biome(41,"river",true,0.5,"none",0.5,new Effects(12638463,null,null,null,null,8103167,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        SAVANNA = vanilla(new Biome(42,"savanna",false,2.0,"none",0.0,new Effects(12638463,null,null,null,null,7254527,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        SAVANNA_PLATEAU = vanilla(new Biome(43,"savanna_plateau",false,2.0,"none",0.0,new Effects(12638463,null,null,null,null,7254527,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        SMALL_END_ISLANDS = vanilla(new Biome(44,"small_end_islands",false,0.5,"none",0.5,new Effects(10518688,null,null,null,null,0,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        SNOWY_BEACH = vanilla(new Biome(45,"snowy_beach",true,0.05,"none",0.3,new Effects(12638463,null,null,null,null,8364543,4020182,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        SNOWY_PLAINS = vanilla(new Biome(46,"snowy_plains",true,0.0,"none",0.5,new Effects(12638463,null,null,null,null,8364543,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        SNOWY_SLOPES = vanilla(new Biome(47,"snowy_slopes",true,-0.3,"none",0.9,new Effects(12638463,null,null,null,null,8560639,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        SNOWY_TAIGA = vanilla(new Biome(48,"snowy_taiga",true,-0.5,"none",0.4,new Effects(12638463,null,null,null,null,8625919,4020182,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        SOUL_SAND_VALLEY = vanilla(new Biome(49,"soul_sand_valley",false,2.0,"none",0.0,new Effects(1787717,null,null,null,"minecraft:ambient.soul_sand_valley.loop",7254527,4159204,329011,new Effects.MoodSound("minecraft:ambient.soul_sand_valley.mood",6000,2,8),null,new Effects.AdditionSound("minecraft:ambient.soul_sand_valley.additions",0.0111),new Effects.Particle("minecraft:ash",0.00625)))),
        SPARSE_JUNGLE = vanilla(new Biome(50,"sparse_jungle",true,0.95,"none",0.8,new Effects(12638463,null,null,null,null,7842047,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        STONY_PEAKS = vanilla(new Biome(51,"stony_peaks",true,1.0,"none",0.3,new Effects(12638463,null,null,null,null,7776511,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        STONY_SHORE = vanilla(new Biome(52,"stony_shore",true,0.2,"none",0.3,new Effects(12638463,null,null,null,null,8233727,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        SUNFLOWER_PLAINS = vanilla(new Biome(53,"sunflower_plains",true,0.8,"none",0.4,new Effects(12638463,null,null,null,null,7907327,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        SWAMP = vanilla(new Biome(54,"swamp",true,0.8,"none",0.9,new Effects(12638463,6975545,null,"swamp",null,7907327,6388580,2302743,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        TAIGA = vanilla(new Biome(55,"taiga",true,0.25,"none",0.8,new Effects(12638463,null,null,null,null,8233983,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        THE_END = vanilla(new Biome(56,"the_end",false,0.5,"none",0.5,new Effects(10518688,null,null,null,null,0,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        THE_VOID = vanilla(new Biome(57,"the_void",false,0.5,"none",0.5,new Effects(12638463,null,null,null,null,8103167,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        WARM_OCEAN = vanilla(new Biome(58,"warm_ocean",true,0.5,"none",0.5,new Effects(12638463,null,null,null,null,8103167,4445678,270131,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        WARPED_FOREST = vanilla(new Biome(59,"warped_forest",false,2.0,"none",0.0,new Effects(1705242,null,null,null,"minecraft:ambient.warped_forest.loop",7254527,4159204,329011,new Effects.MoodSound("minecraft:ambient.warped_forest.mood",6000,2,8),null,new Effects.AdditionSound("minecraft:ambient.warped_forest.additions",0.0111),new Effects.Particle("minecraft:warped_spore",0.01428)))),
        WINDSWEPT_FOREST = vanilla(new Biome(60,"windswept_forest",true,0.2,"none",0.3,new Effects(12638463,null,null,null,null,8233727,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        WINDSWEPT_GRAVELLY_HILLS = vanilla(new Biome(61,"windswept_gravelly_hills",true,0.2,"none",0.3,new Effects(12638463,null,null,null,null,8233727,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        WINDSWEPT_HILLS = vanilla(new Biome(62,"windswept_hills",true,0.2,"none",0.3,new Effects(12638463,null,null,null,null,8233727,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        WINDSWEPT_SAVANNA = vanilla(new Biome(63,"windswept_savanna",false,2.0,"none",0.0,new Effects(12638463,null,null,null,null,7254527,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null))),
        WOODED_BADLANDS = vanilla(new Biome(64,"wooded_badlands",false,2.0,"none",0.0,new Effects(12638463,10387789,9470285,null,null,7254527,4159204,329011,new Effects.MoodSound("minecraft:ambient.cave",6000,2,8),null,null,null)));
}