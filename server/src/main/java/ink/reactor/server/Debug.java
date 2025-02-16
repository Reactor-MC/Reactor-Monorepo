package ink.reactor.server;

import ink.reactor.debug.DebugPlugin;

final class Debug {

    static void startDebugPlugin() {
        final DebugPlugin debugPlugin = new DebugPlugin();
        debugPlugin.load();
        debugPlugin.enable();
    }
}
