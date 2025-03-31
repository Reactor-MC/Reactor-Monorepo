package ink.reactor.world.block.state;

public final class TrialSpawner {
    public static int of(final char id, final boolean ominous, final BlockTrialSpawnerState trialSpawnerState) {
        final String sentence = ominous+"-"+trialSpawnerState;
        return switch(sentence) {
            case "true-INACTIVE" -> id-6;
            case "true-WAITING_FOR_PLAYERS" -> id-5;
            case "true-ACTIVE" -> id-4;
            case "true-WAITING_FOR_REWARD_EJECTION" -> id-3;
            case "true-EJECTING_REWARD" -> id-2;
            case "true-COOLDOWN" -> id-1;
            case "false-WAITING_FOR_PLAYERS" -> id+1;
            case "false-ACTIVE" -> id+2;
            case "false-WAITING_FOR_REWARD_EJECTION" -> id+3;
            case "false-EJECTING_REWARD" -> id+4;
            case "false-COOLDOWN" -> id+5;
            default -> id;
        };
    }
}