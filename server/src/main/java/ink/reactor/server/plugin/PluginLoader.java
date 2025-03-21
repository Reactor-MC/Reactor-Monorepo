package ink.reactor.server.plugin;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
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

            final String classPath = findMainClassFromJar(file);
            if (classPath == null) {
                continue;
            }

            try {
                URL[] urls = {file.toURI().toURL()};
                URLClassLoader classLoader = new URLClassLoader(urls, PluginLoader.class.getClassLoader());

                final Class<?> clazz = Class.forName(classPath, true, classLoader);
                final Plugin plugin = (Plugin) clazz.getDeclaredConstructor().newInstance();

                if (plugin.load()) {
                    plugins.add(plugin);
                    Logger.info("Plugin " + plugin.getName() + " loaded");
                }
            } catch (final Exception e) {
                Logger.error(e, "Error trying to load the plugin " + file);
            }
        }
        return plugins;
    }

    private static String findMainClassFromJar(File jarFile) {
        try (final JarFile jar = new JarFile(jarFile)) {
            final JarEntry entry = jar.getJarEntry("plugin.properties");
            if (entry == null) {
                Logger.warn("The plugin " + jarFile + " does not contain a plugin.properties");
                return null;
            }

            try (InputStream input = jar.getInputStream(entry)) {
                Properties properties = new Properties();
                properties.load(input);
                String mainClass = properties.getProperty("main");

                if (mainClass == null || mainClass.isBlank()) {
                    Logger.warn("The plugin " + jarFile + " does not define a main class in plugin.properties");
                    return null;
                }
                return mainClass;
            }
        } catch (final Exception e) {
            Logger.error(e, "Error reading plugin.yml from " + jarFile);
        }
        return null;
    }
}