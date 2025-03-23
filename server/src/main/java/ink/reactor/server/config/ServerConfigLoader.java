package ink.reactor.server.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

import com.alibaba.fastjson2.JSON;
import ink.reactor.util.DataHolder;
import org.tinylog.Logger;

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

    public ServerConfig load(final YamlConfigManager yaml) {
        final File configFile = new File(serverDirectory, "config.yml");
        Logger.info("Loading config from " + configFile.getAbsolutePath());

        if (!configFile.exists() && !yaml.write("config.yml", configFile)) {
            return null;
        }

        final ConfigSection config = yaml.getConfig(configFile);
        if (config == null) {
            return null;
        }

        final String ip = config.getOrDefault("ip", "localhost");
        final int port = config.getOrDefault("port", 25565);

        Logger.info("Host: {}:{}", ip, port);

        final Motd motd = loadMotd(config.getSection("motd"));
        final ServerConfig serverConfig = new ServerConfig(
                ip, port,
                config.getBoolean("debug-mode"),
                config.getIntMax("ping-wait-update-ticks", 20),
                motd,
                serverDirectory,
                new ServerConfig.Game(
                        config.getIntMax("view-distance", 2),
                        config.getIntMax("simulation-distance", 2)
                ),
                new ServerConfig.Network(
                        config.getBoolean("tcp-fast-open"),
                        config.getIntMax("tcp-fast-open-connections", 1)
                ),
                new DataHolder<>(JSON.toJSONString(motd))
        );

        if (serverConfig.debugMode()) {
            Logger.info("Debug mode enabled");
        }

        return serverConfig;
    }


    private Motd loadMotd(final ConfigSection motdSection) {
        final Motd motd = new Motd();
        motd.setPlayers(new Motd.Players(0, 0, null));
        motd.setVersion(new Motd.Version("1.21.4", 769));
        motd.setDescription(new Motd.Description("A Minecraft Server"));

        if (motdSection == null) {
            Logger.info("Can't found motd section in the config.yml");
            return motd;
        }

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
            final byte[] byteArray = new byte[(int)favicon.length()];
            fis.read(byteArray);
            final String faviconEncoded = Base64.getEncoder().encodeToString(byteArray);
            Logger.info("Favicon loaded successfully ");
            return "data:image/png;base64,"+faviconEncoded;
        } catch (Exception e) {
            Logger.error(e, "Error while encoding favicon to Base64");
            return null;
        }
    }
}