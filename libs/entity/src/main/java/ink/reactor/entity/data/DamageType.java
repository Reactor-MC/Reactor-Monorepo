/**
 * Autogenerated file. Please don't touch if you don't know :)
 * Class generator: ink.reactor.dataparser.type.damage.DamageTypeDataParser (module data-parser)
 * Date: Year: 2025. Month: 03. Day: 31. Hour: 01:14:02 (24h format)
 * <p>
 * Jsons combined using
 *   @see ink.reactor.dataparser.util.JsonCombiner
 * .json files generated with Data generators.
 *   <a href="https://minecraft.wiki/w/Minecraft_Wiki:Projects/wiki.vg_merge/Data_Generators">Data generators</a>
 */
package ink.reactor.entity.data;

import java.util.List;
import java.util.ArrayList;

public final record DamageType(
    String name,
    String deathMessageType,
    String messageId,
    String scaling,
    double exhaustion
) {
    public static final List<DamageType> ALL = new ArrayList<>(49);
    public static final DamageType ARROW = vanilla("arrow","","arrow","when_caused_by_living_non_player",0.1);
    public static final DamageType BAD_RESPAWN_POINT = vanilla("bad_respawn_point","intentional_game_design","badRespawnPoint","always",0.1);
    public static final DamageType CACTUS = vanilla("cactus","","cactus","when_caused_by_living_non_player",0.1);
    public static final DamageType CAMPFIRE = vanilla("campfire","","inFire","when_caused_by_living_non_player",0.1);
    public static final DamageType CRAMMING = vanilla("cramming","","cramming","when_caused_by_living_non_player",0.0);
    public static final DamageType DRAGON_BREATH = vanilla("dragon_breath","","dragonBreath","when_caused_by_living_non_player",0.0);
    public static final DamageType DROWN = vanilla("drown","","drown","when_caused_by_living_non_player",0.0);
    public static final DamageType DRY_OUT = vanilla("dry_out","","dryout","when_caused_by_living_non_player",0.1);
    public static final DamageType ENDER_PEARL = vanilla("ender_pearl","fall_variants","fall","when_caused_by_living_non_player",0.0);
    public static final DamageType EXPLOSION = vanilla("explosion","","explosion","always",0.1);
    public static final DamageType FALL = vanilla("fall","fall_variants","fall","when_caused_by_living_non_player",0.0);
    public static final DamageType FALLING_ANVIL = vanilla("falling_anvil","","anvil","when_caused_by_living_non_player",0.1);
    public static final DamageType FALLING_BLOCK = vanilla("falling_block","","fallingBlock","when_caused_by_living_non_player",0.1);
    public static final DamageType FALLING_STALACTITE = vanilla("falling_stalactite","","fallingStalactite","when_caused_by_living_non_player",0.1);
    public static final DamageType FIREBALL = vanilla("fireball","","fireball","when_caused_by_living_non_player",0.1);
    public static final DamageType FIREWORKS = vanilla("fireworks","","fireworks","when_caused_by_living_non_player",0.1);
    public static final DamageType FLY_INTO_WALL = vanilla("fly_into_wall","","flyIntoWall","when_caused_by_living_non_player",0.0);
    public static final DamageType FREEZE = vanilla("freeze","","freeze","when_caused_by_living_non_player",0.0);
    public static final DamageType GENERIC = vanilla("generic","","generic","when_caused_by_living_non_player",0.0);
    public static final DamageType GENERIC_KILL = vanilla("generic_kill","","genericKill","when_caused_by_living_non_player",0.0);
    public static final DamageType HOT_FLOOR = vanilla("hot_floor","","hotFloor","when_caused_by_living_non_player",0.1);
    public static final DamageType IN_FIRE = vanilla("in_fire","","inFire","when_caused_by_living_non_player",0.1);
    public static final DamageType IN_WALL = vanilla("in_wall","","inWall","when_caused_by_living_non_player",0.0);
    public static final DamageType INDIRECT_MAGIC = vanilla("indirect_magic","","indirectMagic","when_caused_by_living_non_player",0.0);
    public static final DamageType LAVA = vanilla("lava","","lava","when_caused_by_living_non_player",0.1);
    public static final DamageType LIGHTNING_BOLT = vanilla("lightning_bolt","","lightningBolt","when_caused_by_living_non_player",0.1);
    public static final DamageType MACE_SMASH = vanilla("mace_smash","","mace_smash","when_caused_by_living_non_player",0.1);
    public static final DamageType MAGIC = vanilla("magic","","magic","when_caused_by_living_non_player",0.0);
    public static final DamageType MOB_ATTACK = vanilla("mob_attack","","mob","when_caused_by_living_non_player",0.1);
    public static final DamageType MOB_ATTACK_NO_AGGRO = vanilla("mob_attack_no_aggro","","mob","when_caused_by_living_non_player",0.1);
    public static final DamageType MOB_PROJECTILE = vanilla("mob_projectile","","mob","when_caused_by_living_non_player",0.1);
    public static final DamageType ON_FIRE = vanilla("on_fire","","onFire","when_caused_by_living_non_player",0.0);
    public static final DamageType OUT_OF_WORLD = vanilla("out_of_world","","outOfWorld","when_caused_by_living_non_player",0.0);
    public static final DamageType OUTSIDE_BORDER = vanilla("outside_border","","outsideBorder","when_caused_by_living_non_player",0.0);
    public static final DamageType PLAYER_ATTACK = vanilla("player_attack","","player","when_caused_by_living_non_player",0.1);
    public static final DamageType PLAYER_EXPLOSION = vanilla("player_explosion","","explosion.player","always",0.1);
    public static final DamageType SONIC_BOOM = vanilla("sonic_boom","","sonic_boom","always",0.0);
    public static final DamageType SPIT = vanilla("spit","","mob","when_caused_by_living_non_player",0.1);
    public static final DamageType STALAGMITE = vanilla("stalagmite","","stalagmite","when_caused_by_living_non_player",0.0);
    public static final DamageType STARVE = vanilla("starve","","starve","when_caused_by_living_non_player",0.0);
    public static final DamageType STING = vanilla("sting","","sting","when_caused_by_living_non_player",0.1);
    public static final DamageType SWEET_BERRY_BUSH = vanilla("sweet_berry_bush","","sweetBerryBush","when_caused_by_living_non_player",0.1);
    public static final DamageType THORNS = vanilla("thorns","","thorns","when_caused_by_living_non_player",0.1);
    public static final DamageType THROWN = vanilla("thrown","","thrown","when_caused_by_living_non_player",0.1);
    public static final DamageType TRIDENT = vanilla("trident","","trident","when_caused_by_living_non_player",0.1);
    public static final DamageType UNATTRIBUTED_FIREBALL = vanilla("unattributed_fireball","","onFire","when_caused_by_living_non_player",0.1);
    public static final DamageType WIND_CHARGE = vanilla("wind_charge","","mob","when_caused_by_living_non_player",0.1);
    public static final DamageType WITHER = vanilla("wither","","wither","when_caused_by_living_non_player",0.0);
    public static final DamageType WITHER_SKULL = vanilla("wither_skull","","witherSkull","when_caused_by_living_non_player",0.1);
    private static DamageType vanilla(String name, String deathMessageType, String messageId, String scaling, double exhaustion) {
        final DamageType instance = new DamageType(name, deathMessageType, messageId, scaling, exhaustion);
        ALL.add(instance);
        return instance;
    }
}