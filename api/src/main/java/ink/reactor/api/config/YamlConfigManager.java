package ink.reactor.api.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import ink.reactor.api.plugin.Plugin;
import lombok.Getter;
import org.tinylog.Logger;
import org.yaml.snakeyaml.Yaml;

import ink.reactor.util.StringUtil;

@Getter
public class YamlConfigManager {

    private static final File SERVER_FOLDER = new File("");

    private final File datafolder;
    private final Yaml yaml;
    private final String subFolder;
    private final ClassLoader classLoader;

    public YamlConfigManager(File datafolder, Yaml yaml, ClassLoader classLoader) {
        this.datafolder = datafolder;
        this.subFolder = getSubFolderPath(datafolder);
        this.yaml = yaml;
        this.classLoader = classLoader;
    }

    public YamlConfigManager(Plugin plugin) {
        this(new File(SERVER_FOLDER, "plugins/" + plugin.getName()), new Yaml(), plugin.getClass().getClassLoader());
    }

    public ConfigSection getConfig(final String file) {
        final File fileObj = writeInSubFolder(file);
        return (fileObj == null) ? null : getConfig(fileObj);
    }

    public ConfigSection getConfig(final File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return new ConfigSection(yaml.loadAs(reader, Map.class));
        } catch (final IOException e) {
            Logger.error(e, "Error parsing the file " + file);
            return null;
        }
    }

    public File writeInSubFolder(final String file) {
        final File fileObj = new File(datafolder, file);
        if (!fileObj.exists()) {
            if (!createFolder(datafolder)) {
                return null;
            }
            if (!write(subFolder + file, fileObj)) {
                return null;
            }
        }
        return fileObj;
    }

    public boolean write(final String resourcePath, final File outDestination) {
        final InputStream stream = classLoader.getResourceAsStream(resourcePath);
        if (stream == null) {
            Logger.warn("The file " + resourcePath + " don't exist in resources folder");
            return true;
        }
        try {           
            Files.copy(stream, outDestination.toPath());
            return true;
        } catch (final IOException e) {
            Logger.error(e, "Error copying the file " + resourcePath + " into " + outDestination);
            return false;
        }
    }

    public boolean createFolder(final File folder) {
        if (datafolder.exists()) {
            if (datafolder.isDirectory()) {
                return true;
            }
            if (!datafolder.delete()) {
                Logger.warn("The file " + folder.getName() + " is not a folder file");
                return false;
            }
        }
        if (!folder.mkdir()) {
            Logger.warn("Can't create the folder " + folder.getName());
            return false;
        }
        return true;
    }

    /*
     * Example input: plugins/miplugin
     * Output: null
     * 
     * Input: plugins/miplugin/langs
     * Output: langs/
     */
    private static String getSubFolderPath(final File subFolder) {
        final String path = subFolder.getPath();
        final List<String> split = StringUtil.split(path, '/');
        if (split.size() <= 2) {
            return "";
        }
        final StringBuilder builder = new StringBuilder();
        for (int i = 2; i < split.size(); i++) {
            builder.append(split.get(i));
            builder.append('/');
        }
        return builder.toString();
    }
}