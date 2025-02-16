package ink.reactor.api.plugin.service.permission;

import java.util.Collection;
import java.util.Set;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Data;

@Data
public class UserPermissions {
    private Collection<UserRank> ranks;
    private String prefix, suffix;
    private Set<String> permissions;

    public Set<String> getPermissions() {
        return (permissions == null) ? (permissions = new ObjectOpenHashSet<>()) : permissions;
    }
}