package ink.reactor.atom.chat.command;

import ink.reactor.atom.chat.AtomChatPlugin;
import ink.reactor.atom.chat.module.AtomChatModule;
import ink.reactor.atom.chat.module.ModulePersistence;
import ink.reactor.command.CommandSender;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
final class AtomChatModuleState {
    private final AtomChatPlugin plugin;

    private AtomChatModule getModuleByName(final String name) {
        for (final AtomChatModule module : plugin.getModules()) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }

    void reloadModule(final String name, final CommandSender sender) {
        if (name == null) {
            sender.sendMessageLegacy("&cFormat: /achat reload (moduleName)");
            return;
        }
        final AtomChatModule module = getModuleByName(name);
        if (module == null) {
            sender.sendMessageLegacy("&cCan't found the module " + name + ". See all modules with /achat info");
            return;
        }
        if (module.getPersistence() == ModulePersistence.UNMODIFIABLE) {
            sender.sendMessageLegacy("&cThis module is unmodifiable");
            return;
        }
        if (module.disable() && module.loadAndEnable()) {
            sender.sendMessageLegacy("&a" + name + " reloaded!");
            return;
        }
        sender.sendMessageLegacy("&cCan't reload the module. Current state: " + module.getStateName());
    }

    void enableModule(final String name, final CommandSender sender) {
        if (name == null) {
            sender.sendMessageLegacy("&cFormat: /achat enable (moduleName)");
            return;
        }
        final AtomChatModule module = getModuleByName(name);
        if (module == null) {
            sender.sendMessageLegacy("&cCan't found the module " + name + ". See disabled modules with /achat info");
            return;
        }
        if (module.getPersistence() == ModulePersistence.UNMODIFIABLE) {
            sender.sendMessageLegacy("&cThis module is unmodifiable");
            return;
        }
        if (module.loadAndEnable()) {
            sender.sendMessageLegacy("&aThe module " + name + " is now enabled");
            return;
        }
        sender.sendMessageLegacy("&cThis module is already enabled. Current state: " + module.getStateName());
    }

    void disableModule(final String name, final CommandSender sender) {
        if (name == null) {
            sender.sendMessageLegacy("&cFormat: /achat disable (moduleName)");
            return;
        }
        final AtomChatModule module = getModuleByName(name);
        if (module == null) {
            sender.sendMessageLegacy("&cCan't found the module " + name + ". See enabled modules with /achat info");
            return;
        }
        if (module.getPersistence() == ModulePersistence.UNMODIFIABLE) {
            sender.sendMessageLegacy("&cThis module is unmodifiable");
            return;
        }
        if (module.disable()) {
            sender.sendMessageLegacy("&aThe module " + name + " is now disabled");
            return;
        }
        sender.sendMessageLegacy("&cThis module is already disabled. Current state: " + module.getStateName());
    }

    void removeModule(final String name, final CommandSender sender) {
        if (name == null) {
            sender.sendMessageLegacy("&cFormat: /achat remove (moduleName)");
            return;
        }
        final AtomChatModule module = getModuleByName(name);
        if (module == null) {
            sender.sendMessageLegacy("&cCan't found the module " + name + ". See all modules with /achat info");
            return;
        }
        if (module.getPersistence() != ModulePersistence.REMOVABLE) {
            sender.sendMessageLegacy("&cThis module can't be removed. Persistence: " + module.getPersistence().name());
            return;
        }
        plugin.getModules().remove(module);
        sender.sendMessageLegacy("&aNow the module " + name + " is removed!");
    }
}