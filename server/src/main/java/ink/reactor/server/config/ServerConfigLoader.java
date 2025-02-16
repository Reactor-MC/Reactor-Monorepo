package ink.reactor.server.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

import org.tinylog.Logger;

import com.alibaba.fastjson2.JSON;

import ink.reactor.api.config.ConfigSection;
import ink.reactor.api.config.YamlConfigManager;
import ink.reactor.api.config.server.Motd;
import ink.reactor.api.config.server.ServerConfig;
import ink.reactor.chat.format.ChatLegacy;

public final class ServerConfigLoader {

    private final File serverDirectory;

    public ServerConfigLoader(File serverDirectory) {
        this.serverDirectory = serverDirectory;
    }

    public ServerConfig load(final YamlConfigManager fileUtil) {
        final File configFile = new File(serverDirectory, "config.yml");
        Logger.info("Loading config from " + configFile.getAbsolutePath());
        if (!configFile.exists()) {
            fileUtil.write("config.yml", configFile);
        }

        final ConfigSection config = fileUtil.getConfig(configFile);
        if (config == null) {
            Logger.info("Can't found config.yml in the .jar");
            return createDefaultConfig();
        }

        final String ip = config.getOrDefault("ip", "localhost");
        final int port = config.getOrDefault("port", 25565);
        final Motd motd = loadMotd(config.getSection("motd"));
        final int viewDistance = config.getInt("view-distance");
        final int simulationDistance = config.getInt("simulation-distance");

        Logger.info("Host: {}:{}. Motd: {}", ip, port, motd.toString());

        return new ServerConfig(
            ip,
            port,
            JSON.toJSONString(motd),
            viewDistance > 2 ? viewDistance : 2,
            simulationDistance > 2 ? simulationDistance : 2,
            config.getOrDefault("network-compression-threshold", -1)
        );
    }

    private Motd loadMotd(final ConfigSection motdSection) {
        if (motdSection == null) {
            Logger.info("Can't found motd section in the config.yml");
            return Motd.defaultMotd();
        }

        final Motd motd = Motd.defaultMotd();
        final String line1 = motdSection.getOrDefault("line1", "A reactor server");
        final String line2 = motdSection.getOrDefault("line2", "Example of motd");
        final int protocol = motdSection.getOrDefault("protocol-version", 767);
        final String versionName = motdSection.getOrDefault("version-name", "1.21.1");

        motd.setVersion(new Motd.Version(versionName, protocol));
        motd.setDescription(new Motd.Description(ChatLegacy.color(line1 + '\n' + line2)));

        motd.setFavicon(loadFavicon(new File(serverDirectory, "server-icon.png")));

        return motd;
    }

    private String loadFavicon(final File favicon) {
        if (!favicon.exists()) {
            Logger.info("Can't found server-icon.png in the server directory");
            return null;
        }
        try (FileInputStream fis = new FileInputStream(favicon)) {
            final byte byteArray[] = new byte[(int)favicon.length()];
            fis.read(byteArray);
            final String faviconEncoded = Base64.getEncoder().encodeToString(byteArray);
            Logger.info("Favicon loaded successfully ");
            return "data:image/png;base64,"+faviconEncoded;
        } catch (Exception e) {
            Logger.error(e, "Error while encoding favicon to Base64");
            return null;
        }
    }

    private ServerConfig createDefaultConfig() {
        return new ServerConfig("localhost", 25565, JSON.toJSONString(Motd.defaultMotd()), 10, 8, -1);    
    }
}
