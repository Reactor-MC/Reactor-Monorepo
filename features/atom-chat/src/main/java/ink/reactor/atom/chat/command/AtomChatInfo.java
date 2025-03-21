package ink.reactor.atom.chat.command;

import ink.reactor.api.plugin.Plugin;
import ink.reactor.atom.chat.module.AtomChatModule;

import java.util.List;

final class AtomChatInfo {
    static String getModulesInfo(final List<AtomChatModule> modules) {
        final StringBuilder builder = new StringBuilder();
        builder.append(
                """
                Persistence type:
                    ALWAYS: Can't remove, but can disable and reload
                    REMOVABLE: Can remove, disable and reload
                    UNMODIFIABLE: Can't be removed, disable or reloaded
                Modules info:
                """
        );

        for (final AtomChatModule module : modules) {
            if (module.getState() == Plugin.DISABLED) {
                builder.append("&c ");
            } else if (module.getState() == Plugin.ENABLED) {
                builder.append("&a ");
            } else {
                builder.append("&e ");
            }
            builder.append(module.getName());
            builder.append(" = ");
            builder.append(module.getStateName());
            builder.append(" - ");
            builder.append(module.getPersistence().name());
            builder.append('\n');
        }
        return builder.toString();
    }
}