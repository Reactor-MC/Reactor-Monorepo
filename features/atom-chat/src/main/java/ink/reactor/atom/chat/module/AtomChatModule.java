package ink.reactor.atom.chat.module;

import ink.reactor.api.plugin.Plugin;

public abstract class AtomChatModule extends Plugin {
    public ModulePersistence getPersistence() {
        return ModulePersistence.ALWAYS;
    }
}