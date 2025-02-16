package ink.reactor.api.player.event;

import ink.reactor.api.player.Player;

public final class PlayerJoinEvent {
    private final Player player;

    public PlayerJoinEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}