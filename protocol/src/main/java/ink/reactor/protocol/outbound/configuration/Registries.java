package ink.reactor.protocol.outbound.configuration;

import ink.reactor.api.entity.data.DamageType;
import ink.reactor.api.world.block.Painting;
import ink.reactor.api.world.data.Biome;
import ink.reactor.api.world.data.Biome.Effects;
import ink.reactor.api.world.data.WorldType;
import ink.reactor.entity.data.wolf.WolfType;
import ink.reactor.item.data.Banner;
import ink.reactor.item.data.armor.TrimMaterial;
import ink.reactor.item.data.armor.TrimPattern;
import ink.reactor.nbt.type.NBTGeneral;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.protocol.outbound.CachedPacket;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

public final class Registries {

    public static CachedPacket packet(final byte[] data) {
        return new CachedPacket(data, OutProtocol.CONFIGURATION_REGISTRY_DATA);
    }

    public static byte[] wolfVariants() {
        final int amountWolfs = WolfType.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amountWolfs * 64);

        buffer.writeString("minecraft:wolf_variant");
        buffer.writeVarInt(amountWolfs);

        for (final WolfType wolfType : WolfType.ALL) {
            buffer.writeString("minecraft:"+wolfType.getName());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();
            nbt.addString("wild_texture", wolfType.getWildTexture());
            nbt.addString("tame_texture", wolfType.getTameTexture());
            nbt.addString("angry_texture", wolfType.getAngryTexture());
            nbt.addString("biomes", "minecraft:badlands");
            NBTByteWriter.writeNBT(nbt, buffer);
        }
        return buffer.compress();
    }

    public static byte[] damageTypes() {
        final int amount = DamageType.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 32);

        buffer.writeString("minecraft:damage_type");
        buffer.writeVarInt(amount);
    
        for (final DamageType damageType : DamageType.ALL) {
            buffer.writeString("minecraft:"+damageType.getName());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();
            nbt.addString("message_id", damageType.getMessageId());
            nbt.addString("scaling", damageType.getScaling());
            nbt.addFloat("exhaustion", (float)damageType.getExhaustion());
            nbt.addString("effects", "hurt");

            NBTByteWriter.writeNBT(nbt, buffer);
        }
        return buffer.compress();
    }

    public static byte[] trimMaterial() {
        final int amount = TrimMaterial.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 128);

        buffer.writeString("minecraft:trim_material");
        buffer.writeVarInt(amount);
    
        for (final TrimMaterial material : TrimMaterial.ALL) {
            buffer.writeString("minecraft:"+material.getAssetName());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();
            nbt.addString("asset_name", material.getAssetName());
            nbt.addString("ingredient", material.getIngredient());
            nbt.addFloat("item_model_index", (float)material.getModelIndex());
            nbt.addString("description", material.getDescription());

            NBTByteWriter.writeNBT(nbt, buffer);
        }
        return buffer.compress();
    }

    public static byte[] trimPattern() {
        final int amount = TrimPattern.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 32);

        buffer.writeString("minecraft:trim_pattern");
        buffer.writeVarInt(amount);
    
        for (final TrimPattern pattern : TrimPattern.ALL) {
            buffer.writeString(pattern.getAssetId());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();
            nbt.addString("asset_id", pattern.getAssetId());
            nbt.addString("template_item", pattern.getTemplateItem());
            nbt.addString("description", pattern.getDescription());
            nbt.addBoolean("decal", pattern.isDecal());
            NBTByteWriter.writeNBT(nbt, buffer);
        }
        return buffer.compress();
    }

    public static byte[] banner() {
        final int amount = Banner.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 48);

        buffer.writeString("minecraft:banner_pattern");
        buffer.writeVarInt(amount);
    
        for (final Banner banner : Banner.ALL) {
            buffer.writeString(banner.getAssetId());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();
            nbt.addString("asset_id", banner.getAssetId());
            nbt.addString("translation_key", banner.getTranslationKey());
            NBTByteWriter.writeNBT(nbt, buffer);
        }
        return buffer.compress();
    }

    public static byte[] painting() {
        final int amount = Painting.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 18);

        buffer.writeString("minecraft:painting_variant");
        buffer.writeVarInt(amount);
    
        for (final Painting painting : Painting.ALL) {
            buffer.writeString(painting.getAssetId());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();
            nbt.addString("asset_id", painting.getAssetId());
            nbt.addInt("height", painting.getHeight());
            nbt.addInt("width", painting.getWidth());
            NBTByteWriter.writeNBT(nbt, buffer);
        }
        return buffer.compress();
    }

    public static byte[] dimensionTypes() {
        final int amount = WorldType.ALL.length;
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 18);

        buffer.writeString("minecraft:dimension_type");
        buffer.writeVarInt(amount);
    
        for (final WorldType world : WorldType.ALL) {
            buffer.writeString("minecraft:"+world.name());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();

            nbt.addLong("fixed_time", world.fixedTime());
            nbt.addBoolean("has_skylight", world.hasSkyLight());
            nbt.addBoolean("has_ceiling", world.hasCeiling());
            nbt.addBoolean("ultrawarm", world.ultrawarm());
            nbt.addBoolean("natural", world.natural());
            nbt.addDouble("coordinate_scale", world.coordinateScale());
            nbt.addBoolean("bed_works", world.bedWorks());
            nbt.addBoolean("respawn_anchor_works", world.respawnAnchorWorks());
            nbt.addInt("min_y", world.minY());
            nbt.addInt("height", world.height());
            nbt.addInt("logical_height", world.localHeight());
            nbt.addString("infiniburn", world.infiniburn());
            nbt.addString("effects", world.effects());
            nbt.addFloat("ambient_light", (float)world.ambientLight());
            nbt.addBoolean("piglin_safe", world.piglinSafe());
            nbt.addBoolean("has_raids", world.hasRaids());
            nbt.addInt("monster_spawn_light_level", world.monsterSpawnLightLevel());
            nbt.addInt("monster_spawn_block_light_limit", world.monsterSpawnBlockLightLimit());

            NBTByteWriter.writeNBT(nbt, buffer);
        }
        return buffer.compress();
    }

    public static byte[] biome() {
        final int amount = Biome.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 256);

        buffer.writeString("minecraft:worldgen/biome");
        buffer.writeVarInt(amount);
    
        for (final Biome biome : Biome.ALL) {
            buffer.writeString("minecraft:"+biome.name());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();
            nbt.addBoolean("has_precipitation", biome.hasPrecipitation());
            nbt.addFloat("temperature", (float)biome.temperature());
            nbt.addFloat("downfall", (float)biome.downFall());
            nbt.addString("temperature_modifier", biome.temperatureModifier() == null ? "none" : biome.temperatureModifier());

            nbt.addCompound("effects", createEffects(biome.effects()));
            NBTByteWriter.writeNBT(nbt, buffer);
        }
        return buffer.compress();
    }

    private static NBTGeneral createEffects(final Effects effects) {
        final NBTGeneral nbt = new NBTGeneral();
        nbt.addInt("fog_color", effects.fogColor());
        nbt.addInt("water_color", effects.waterColor());
        nbt.addInt("water_fog_color", effects.waterFogColor());
        nbt.addInt("sky_color", effects.skyColor());

        if (effects.foliageColor() != null) {
            nbt.addInt("foliage_color", effects.foliageColor());
        }
        if (effects.grassColor() != null) {
            nbt.addInt("grass_color", effects.grassColor());
        }
        nbt.addString("grass_color_modifier", effects.grassColorModifier() == null ? "none" : effects.grassColorModifier());

        if (effects.moodSound() != null) {
            nbt.addCompound("mood_sound", createMoodSound(effects.moodSound()));
        }

        if (effects.additionSound() != null) {
            nbt.addCompound("additions_sound", createAdditionSound(effects.additionSound()));
        }

        if (effects.music() != null) {
            nbt.addCompound("music", createMusic(effects.music()));
        }

        return nbt;
    }

    private static NBTGeneral createMoodSound(final Effects.MoodSound sound) {
        final NBTGeneral nbt = new NBTGeneral();
        nbt.addString("sound", sound.sound());
        nbt.addInt("block_search_extent", sound.blockSearchExtent());
        nbt.addInt("tick_delay", sound.tickDelay());
        nbt.addDouble("offset", sound.offSet());
        return nbt;
    }

    private static NBTGeneral createAdditionSound(final Effects.AdditionSound sound) {
        final NBTGeneral nbt = new NBTGeneral();
        nbt.addString("sound", sound.sound());
        nbt.addDouble("tick_chance", sound.tickChance());
        return nbt;
    }

    private static NBTGeneral createMusic(final Effects.Music music) {
        final NBTGeneral nbt = new NBTGeneral();
        nbt.addString("sound", music.sound());
        nbt.addInt("min_delay", music.minDelay());
        nbt.addInt("max_delay", music.maxDelay());
        nbt.addBoolean("replace_current_music", music.replaceCurrentMusic());
        return nbt;
    }
}