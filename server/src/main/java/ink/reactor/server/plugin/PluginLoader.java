package ink.reactor.server.plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.tinylog.Logger;

import ink.reactor.api.plugin.Plugin;

final class PluginLoader {
    
    static List<Plugin> load(final File pluginFolder) {
        if (!pluginFolder.exists()) {
            pluginFolder.mkdir();
        }

        final File[] files = pluginFolder.listFiles();
        if (files == null) {
            return List.of();
        }

        final List<Plugin> plugins = new ArrayList<>(files.length);
        for (final File file : files) {
            if (!file.getName().endsWith(".jar")) {
                continue;
            }

            final String classPath = findClassNamesFromJar(file);
            if (classPath == null) {
                continue;
            }

            try (final URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()}, PluginLoader.class.getClassLoader())) {
                final Class<?> clazz = classLoader.loadClass(classPath);
                final Plugin plugin = (Plugin) clazz.getDeclaredConstructor().newInstance();
                plugin.load();
                plugins.add(plugin);                  
                Logger.info("Plugin " + plugin.getName() + " loaded");
            } catch (final Exception e) {
                Logger.error(e, "Error trying to load the plugin " + file);
            }
        }
        return plugins;
    }

    private static String findClassNamesFromJar(File jarFile) {
        try (final JarFile jar = new JarFile(jarFile)) {
            final Iterator<JarEntry> enumerations = jar.entries().asIterator();
            while (enumerations.hasNext()) {
                final JarEntry entry = enumerations.next();
                if (entry.getName().endsWith(".class")) {
                    return entry.getName()
                        .replace('/', '.')
                        .replace(".class", "");
                }
            }
            Logger.warn("The plugin " + jarFile + " does not contain any class");
        } catch (final Exception e) {
            Logger.error(e, "Error trying to load the plugin " + jarFile);
        }
        return null;
    }
}