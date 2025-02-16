package ink.reactor.item.data.consumable;

public record Consumable(
    float durationSeconds,
    ConsumableAnimation animation,
    String sound,
    boolean hasParticles
    // TODO: Effects
) {}
