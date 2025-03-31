package ink.reactor.world.block.state;

public enum BlockTrialSpawnerState {
    INACTIVE,
    WAITING_FOR_PLAYERS,
    ACTIVE,
    WAITING_FOR_REWARD_EJECTION,
    EJECTING_REWARD,
    COOLDOWN;
}