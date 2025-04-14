package ink.reactor.protocol.outbound.configuration;

import ink.reactor.world.block.PaintingVariant;
import ink.reactor.world.data.Biome;
import ink.reactor.world.data.Biome.Effects;
import ink.reactor.entity.data.DamageType;
import ink.reactor.entity.type.wolf.WolfVariant;
import ink.reactor.item.data.armor.TrimMaterial;
import ink.reactor.item.data.armor.TrimPattern;
import ink.reactor.nbt.type.NBTGeneral;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.protocol.outbound.CachedPacket;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.writer.DynamicSizeBuffer;
import ink.reactor.world.block.BannerPattern;
import ink.reactor.world.data.DimensionType;

public final class Registries {

    public static CachedPacket packet(final byte[] data) {
        return new CachedPacket(data, OutProtocol.CONFIGURATION_REGISTRY_DATA);
    }

    public static byte[] wolfVariants() {
        final int amountWolfs = WolfVariant.ALL.size();
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(amountWolfs * 64);

        buffer.writeString("minecraft:wolf_variant");
        buffer.writeVarInt(amountWolfs);

        for (final WolfVariant wolfVariant : WolfVariant.ALL) {
            buffer.writeString("minecraft:"+wolfVariant.name());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();
            nbt.addString("wild_texture", wolfVariant.wildTexture());
            nbt.addString("tame_texture", wolfVariant.tameTexture());
            nbt.addString("angry_texture", wolfVariant.angryTexture());
            nbt.addString("biomes", "minecraft:badlands");
            NBTByteWriter.writeNBT(nbt, buffer);
        }

        return buffer.compress();
    }

    public static byte[] damageTypes() {
        final int amount = DamageType.ALL.size();
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(amount * 32);

        buffer.writeString("minecraft:damage_type");
        buffer.writeVarInt(amount);
    
        for (final DamageType damageType : DamageType.ALL) {
            buffer.writeString("minecraft:"+damageType.name());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();
            nbt.addString("message_id", damageType.messageId());
            nbt.addString("scaling", damageType.scaling());
            nbt.addFloat("exhaustion", (float)damageType.exhaustion());
            nbt.addString("effects", "hurt");

            NBTByteWriter.writeNBT(nbt, buffer);
        }
        return buffer.compress();
    }

    public static byte[] trimMaterial() {
        final int amount = TrimMaterial.ALL.size();
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(amount * 128);

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
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(amount * 32);

        buffer.writeString("minecraft:trim_pattern");
        buffer.writeVarInt(amount);
    
        for (final TrimPattern pattern : TrimPattern.ALL) {
            buffer.writeString(pattern.assetId());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();
            nbt.addString("asset_id", pattern.assetId());
            nbt.addString("template_item", pattern.templateItem());
            nbt.addString("description", pattern.description());
            nbt.addBoolean("decal", pattern.decal());
            NBTByteWriter.writeNBT(nbt, buffer);
        }
        return buffer.compress();
    }

    public static byte[] banner() {
        final int amount = BannerPattern.ALL.size();
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(amount * 48);

        buffer.writeString("minecraft:banner_pattern");
        buffer.writeVarInt(amount);
    
        for (final BannerPattern banner : BannerPattern.ALL) {
            buffer.writeString(banner.assetId());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();
            nbt.addString("asset_id", banner.assetId());
            nbt.addString("translation_key", banner.translationKey());
            NBTByteWriter.writeNBT(nbt, buffer);
        }
        return buffer.compress();
    }

    public static byte[] painting() {
        final int amount = PaintingVariant.ALL.size();
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(amount * 18);

        buffer.writeString("minecraft:painting_variant");
        buffer.writeVarInt(amount);
    
        for (final PaintingVariant painting : PaintingVariant.ALL) {
            buffer.writeString(painting.assetId());
            buffer.writeBoolean(true);

            final NBTGeneral nbt = new NBTGeneral();
            nbt.addString("asset_id", painting.assetId());
            nbt.addInt("height", painting.height());
            nbt.addInt("width", painting.width());
            NBTByteWriter.writeNBT(nbt, buffer);
        }
        return buffer.compress();
    }

    public static byte[] dimensionTypes() {
        final int amount = DimensionType.ALL.size();
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(amount * 18);

        buffer.writeString("minecraft:dimension_type");
        buffer.writeVarInt(amount);
    
        for (final DimensionType world : DimensionType.ALL) {
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
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(amount * 256);

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