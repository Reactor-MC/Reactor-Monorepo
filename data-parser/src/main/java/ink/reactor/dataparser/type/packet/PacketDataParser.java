package ink.reactor.dataparser.type.packet;

import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.common.ParserClass;
import ink.reactor.dataparser.common.ParserFiles;
import ink.reactor.fission.classes.JavaClass;
import ink.reactor.fission.commentary.MultiLineCommentary;
import ink.reactor.fission.field.JavaFields;

import java.io.IOException;
import java.util.Map;

public class PacketDataParser implements DataParser {

    private static final String
            IN_CLASS_NAME = "InProtocol",
            OUT_CLASS_NAME = "OutProtocol";

    @Override
    public void parse(String packageName) throws IOException {
        final JSONObject jsonObject = ParserFiles.loadJsonObject("packets.json");

        final Object commentary = MultiLineCommentary.of(ParserClass.DATA_GENERATOR);

        final JavaClass inbound = new JavaClass(packageName, IN_CLASS_NAME);
        final JavaClass outbound = new JavaClass(packageName, OUT_CLASS_NAME);
        inbound.setCommentary(commentary);
        outbound.setCommentary(commentary);

        parse("clientbound", jsonObject, inbound);
        parse("serverbound", jsonObject, outbound);

        ParserFiles.writeJava(inbound.toString(), IN_CLASS_NAME);
        ParserFiles.writeJava(outbound.toString(), OUT_CLASS_NAME);
    }

    private void parse(final String bound, final JSONObject object, final JavaClass javaClass) {
        for (final Map.Entry<String, Object> entry : object.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject state)) {
                continue;
            }
            final JSONObject boundJson = state.getJSONObject(bound);
            if (boundJson == null) {
                continue;
            }

            for (final Map.Entry<String, Object> packet : boundJson.entrySet()) {
                final JSONObject packetJson = (JSONObject)packet.getValue();
                final String packetName = (entry.getKey() + "_" + packet.getKey().split(":")[1]).toUpperCase();
                javaClass.addFields(JavaFields.STATIC_CONSTANTS.ofInt(packetName, packetJson.getIntValue("protocol_id")));
            }
        }
    }
}
