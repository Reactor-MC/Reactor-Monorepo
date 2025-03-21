package ink.reactor.command;

import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.format.ChatLegacy;

public interface CommandSender {
    void sendMessage(final ChatComponent[] components);
    void sendMessage(final ChatComponent component);
    void sendMessage(final String message);
    void sendMessage(final String... messages);

    default void sendMessageLegacy(final String message) {
        sendMessage(ChatLegacy.format(message));
    }

    boolean isOp();
    boolean hasPermission(final String permission);

    String getName();
}