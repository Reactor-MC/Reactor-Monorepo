package ink.reactor.api.plugin.service.permission;

import java.util.Set;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Data;

@Data
public class Rank {
    private final String id;
    private final int priority;

    private Set<String> permissions;
    private String prefix, suffix;

    public Set<String> getPermissions() {
        return (permissions == null) ? (permissions = new ObjectOpenHashSet<>()) : permissions;
    }

    @Override
    public final boolean equals(final Object obj) {
        return obj == this || (obj instanceof Rank rank && rank.id.equals(this.id));
    }

    @Override
    public final int hashCode() {
        return priority;
    }
}