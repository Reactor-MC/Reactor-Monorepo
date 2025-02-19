package ink.reactor.api.command;

import java.util.Collection;

import ink.reactor.api.Reactor;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.format.ChatLegacy;
import ink.reactor.api.plugin.service.permission.PermissionService;

public interface CommandSender {
    void sendMessage(final Collection<ChatComponent> components);
    void sendMessage(final ChatComponent component);
    void sendMessage(final String message);
    void sendMessage(final String... messages);

    default void sendMessageLegacy(final String message) {
        sendMessage(ChatLegacy.format(message));
    }

    default boolean isOp() {
        final PermissionService permissionService = Reactor.getServer().getPluginManager().getData(PermissionService.class);
        return (permissionService == null) ? false : permissionService.isOp(getName());
    }

    default boolean hasPermission(final String permission) {
        final PermissionService permissionService = Reactor.getServer().getPluginManager().getData(PermissionService.class);
        return (permissionService == null) ? false : permissionService.hasPermission(getName(), permission);
    }

    String getName();
}
