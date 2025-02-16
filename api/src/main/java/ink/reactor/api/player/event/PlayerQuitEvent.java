package ink.reactor.api.player.event;

import ink.reactor.api.player.Player;

public record PlayerQuitEvent(
    Player player
) {
    
}
