package ink.reactor.atom.chat.module;

public enum ModulePersistence {
    // Can't remove, but can disable and reload
    ALWAYS,

    // Can remove, disable and reload
    REMOVABLE,

    // Can't be removed, disable or reloaded
    UNMODIFIABLE
}