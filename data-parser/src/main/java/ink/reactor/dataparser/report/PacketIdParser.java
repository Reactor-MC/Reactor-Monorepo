package ink.reactor.dataparser.report;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;

public final class PacketIdParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("report/packet");
        final JSONObject packets = loadJsonObject("report/packets.json");

        createIdPackets("clientbound", "Out", template, packets);
        createIdPackets("serverbound", "In", template, packets);
    }

    private void createIdPackets(final String bound, final String name, String template, final JSONObject packets) throws IOException {
        template = template
            .replace("{NAME}", name)
            .replace("{HANDSHAKE}", loadState(packets, "handshake", bound))
            .replace("{LOGIN}", loadState(packets, "login", bound))
            .replace("{PLAY}", loadState(packets, "play", bound))
            .replace("{CONFIGURATION}", loadState(packets, "configuration", bound));

        writeFile(getClass(), template, name + "Protocol");
    }

    private String loadState(final JSONObject packets, final String state, final String bound) {
        final JSONObject packetsBound = packets.getJSONObject(state).getJSONObject(bound);
        if (packetsBound == null) {
            return "";
        }

        final StringBuilder builder = new StringBuilder();
        final Set<Entry<String, Object>> entries = packetsBound.entrySet();

        int i = 0;

        final String packetPrefix = state.toUpperCase() + "_";

        for (final Entry<String, Object> entry : entries) {
            builder.append('\n');
            final String fieldName = toFieldName(entry.getKey());
            final int id = ((JSONObject)entry.getValue()).getIntValue("protocol_id");

            builder.append("        ");
            builder.append(packetPrefix);
            builder.append(fieldName);
            builder.append(" = ");
            builder.append(id);

            if (++i != entries.size()) {
                builder.append(',');
            } else {
                builder.append(';');
            }
        }
        return builder.toString();
    }
}