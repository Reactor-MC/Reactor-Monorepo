package ink.reactor.api.config.server;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Motd {

    private Players players;
    private Version version;
    private boolean enforcesSecureChat = false;
    private String favicon;

    private Description description;

    public static record Version(
        String name,
        int protocol
    ){}

    public static record Players(
        int max,
        int online,
        SamplePlayer[] sample
    ) {}
    public static record SamplePlayer(
        String name,
        String id
    ){}

    public static record Description(
        String text
    ){}

    public static Motd defaultMotd() {
        final Motd motd = new Motd();
        motd.players = new Players(0, 0, null);
        motd.version = new Version("1.21.4", 769);
        motd.description = new Description("A Minecraft Server");
        return motd;
    }

    @Override
    public String toString() {
        return "Motd: " +
                players + ", " +
                version + ", " +
                description;
    }
}