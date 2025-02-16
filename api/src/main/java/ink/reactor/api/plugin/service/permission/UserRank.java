package ink.reactor.api.plugin.service.permission;

import java.time.Instant;

public record UserRank(
    Rank rank,
    Instant addedTime,
    Instant expirationTime
){
    public boolean isPermanent() {
        return expirationTime == null;
    }
}