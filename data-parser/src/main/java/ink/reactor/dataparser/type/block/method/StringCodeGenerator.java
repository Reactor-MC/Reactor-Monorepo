package ink.reactor.dataparser.type.block.method;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import ink.reactor.fission.method.JavaMethod;
import ink.reactor.fission.method.JavaMethodParameter;

import java.util.Collection;

public class StringCodeGenerator implements BlockMethodCodeGenerator {

    @Override
    public String generateMethodCode(final JavaMethod method, final JSONArray blockStates, final int defaultId) {
        final StringBuilder methodCode = new StringBuilder();

        appendSentence(methodCode, method.getParameters());
        appendSwitch(methodCode, blockStates, defaultId);

        return methodCode.toString();
    }

    public void appendSentence(final StringBuilder builder, final Collection<JavaMethodParameter> parameters) {
        builder.append("final String sentence = ");
        final int size = parameters.size()-1;
        int i = 0;
        for (final JavaMethodParameter parameter : parameters) {
            if (parameter.getName().equals("id")) {
                continue;
            }
            if (i == 0) {
                builder.append("String.valueOf(").append(parameter.getName()).append(')');
            } else {
                builder.append(parameter.getName());
            }

            if (++i != size) {
                builder.append("+'-'+"); // Add parameter separator
            } else {
                builder.append(';');
            }
        }
    }

    public void appendSwitch(final StringBuilder builder, final JSONArray blockStates, final int defaultId) {
        builder.append("\nreturn switch(sentence) {\n");
        for (final Object object : blockStates) {
            if (!(object instanceof JSONObject state)) {
                continue;
            }
            final int id = state.getIntValue("id");
            final JSONObject properties = state.getJSONObject("properties");
            final int differenceId = id - defaultId;

            builder
                .append("    case ")
                .append(combineStates(properties))
                .append(" -> id");

            if (differenceId < 0) {
                builder.append(differenceId);
            } else if (differenceId > 0) {
                builder.append('+').append(differenceId);
            }

            builder.append(';').append('\n');
        }
        builder.append("    default -> -1;\n");
        builder.append("};");
    }

    // Example input: ["floor","north","false"]
    // Example output: "floor-north-false"
    private String combineStates(final JSONObject states) {
        final StringBuilder builder = new StringBuilder();
        int i = 0;
        final Collection<Object> values = states.values();
        final int size = values.size();

        builder.append('"');
        for (final Object value : values) {
            builder.append(value);
            if (++i != size) {
                builder.append('-');
            }
        }
        builder.append('"');

        return builder.toString();
    }
}